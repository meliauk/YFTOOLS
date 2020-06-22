package com.yifan.controller;


import com.yifan.entity.User;
import com.yifan.service.UserService;
import com.yifan.util.MD5SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("All")
    public List allUser(){
        return userService.list();
    }


    @PostMapping("updateUser")
    public String updateUser(String name,
                             String password,
                             @RequestParam(required = false)  String email,
                             @RequestParam(required = false)  String phone,
                             @RequestParam(required = false)  String info,
                             Long id){
        User user = new User();
        user.setUid(id)
                .setUemail(email).setUname(name)
                .setUpassword(MD5SaltUtil.MD5Salt(name.trim(),password))
                .setUinfo(info).setUphone(phone).setGmtModified(LocalDateTime.now());
        boolean update = userService.updateById(user);
        if(true == update){
            return "更新用户信息成功";
        }else {
            return "更新用户信息失败";
        }
    }
}

