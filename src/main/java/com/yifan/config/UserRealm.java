package com.yifan.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yifan.entity.User;
import com.yifan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/* 自定义的 UserRealm */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;


   // 授权
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(
       PrincipalCollection principalCollection) {
       System.out.println("--------------------授权--------------------");
       // SimpleAuthorizationInfo授权; SimpleAuthenticationInfo认证
       SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       Subject subject = SecurityUtils.getSubject();
       // 拿到user对象--拿到的是由认证返回的用户对象
       User currentUser = (User) subject.getPrincipal();
       // 设置当前用户的权限--权限名称可以从数据库中取
       String upower = currentUser.getUpower();

       return null;
    }
   
   // 认证
   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       System.out.println("--------------------认证--------------------");
       // 得到封装的用户数据
       UsernamePasswordToken userToken = (UsernamePasswordToken) token;
       LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
       wrapper.eq(User::getUname,userToken.getUsername()).or().
               eq(User::getUemail,userToken.getUsername()).or().
               eq(User::getUphone,userToken.getUsername());

       User one = userService.getOne(wrapper);
       // 用户存到session中
       Subject subject = SecurityUtils.getSubject();
       subject.getSession().setAttribute("user",one);


       if(StringUtils.isEmpty(one)){
           return null ;
       }

       /*SimpleAuthenticationInfo(用户名/用户，密码，盐值，当前的Realm)*/
       return new SimpleAuthenticationInfo(one,one.getUpassword(), ByteSource.Util.bytes(one.getUname()),getName());
  }
}