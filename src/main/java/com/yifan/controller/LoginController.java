package com.yifan.controller;

import com.yifan.entity.User;
import com.yifan.service.UserService;
import com.yifan.util.MD5SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","login"})
    public String login(HttpSession session){
        System.err.println("----记住我状态---->"+SecurityUtils.getSubject().isRemembered());
        if(SecurityUtils.getSubject().isRemembered()){
            Subject currentUser = SecurityUtils.getSubject();
            User user = (User) currentUser.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        }else {
            return "login";
        }
    }

    @GetMapping("reg")
    public String reg(){
        return "reg";
    }

    /*------------登录*/
    @PostMapping("toIndex")
    public String toindex(String name , String password ,boolean rememberMe , Model model, HttpSession session) {

        Subject subject = SecurityUtils.getSubject();

        // 封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(name.trim(), password);
        System.err.println(rememberMe+"=======");
        try {
            // 记住我功能~~~~~~-------------
            token.setRememberMe(rememberMe);
            // 登录
            subject.login(token);
            session.setAttribute("showPassword",password);
            return "redirect:/user/index";
        }catch (UnknownAccountException e){//用户名不正确
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch ( IncorrectCredentialsException e){//密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }catch ( LockedAccountException lae ) {
            model.addAttribute("msg","用户被锁定，不能登录");
            return "login";
        }
        // isAuthenticated

    }

    /*------------注册*/
    @PostMapping("toLogin")
    public String tologin(String name ,String email ,String password ,Model model){
        // md5 盐值 解密
        String simpleHash = MD5SaltUtil.MD5Salt(name, password);
        User user = new User();
        user.setUname(name).
                setUemail(email).
                setUpassword(simpleHash).
                setGmtCreate(LocalDateTime.now()).
                setUtoken(UUID.randomUUID()+simpleHash);
        boolean save = userService.save(user);
        if(save){
            return "login";
        }else {
            return "reg";
        }

    }

    @GetMapping("noAuth")
    public String noAuth(){
        return "noAuth";
    }


}
