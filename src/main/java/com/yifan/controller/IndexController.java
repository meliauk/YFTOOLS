package com.yifan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Controller
@RequestMapping("user")
public class IndexController {


    /*----------退出 */
    @GetMapping("logout")
    public String logout(HttpSession session, Model model){
        Subject subject = SecurityUtils.getSubject();
        Serializable id = subject.getSession().getId();
        subject.logout();
        model.addAttribute("msg","安全退出");
        return "redirect:/login";
    }

    /*----------首页 */
    @GetMapping("index")
    public String index(){
        return "index";
    }

    /*----------工具 */
    @GetMapping("tools")
    public String tools(){
        return "tools";
    }

    /*----------账户 */
    @GetMapping("account")
    public String account(){
        return "account";
    }

    /*----------信息 */
    @GetMapping("profile")
    public String profile(){
        return "profile";
    }

    /*----------邀请 */
    @GetMapping("invite")
    public String invite(){
        return "invite";
    }

    /*----------其他-更新计划 */
    @GetMapping("plan")
    public String plan(){
        return "plan";
    }





}
