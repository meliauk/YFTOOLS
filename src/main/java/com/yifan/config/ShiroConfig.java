package com.yifan.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间7天,单位秒;
        simpleCookie.setMaxAge(7*24*60*60);
        simpleCookie.setName("rememberMe");
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // cookieRememberMeManager.setCipherKey用来设置加密的Key,参数类型byte[],字节数组长度要求16
        // cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }


    @Bean("myCacheManager")
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存  传入的是用户的字段
        // 否则报错  class com.yifan.entity.User must has getter for field: id
        redisCacheManager.setPrincipalIdFieldName("uid");
        return redisCacheManager;
    }
    // 它可对redis的ip、端口等进行配置
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setTimeout(timeout);
        return redisManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    //自定义sessionManager
    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 取消登录成功后url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }


    /**   告诉 shiro 是经过加密的
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


   // 3.创建      Filter工厂，设置对应的过滤条件和跳转条件
   @Bean
   public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
       ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
       // 设置 安全管理器
       factoryBean.setSecurityManager(defaultWebSecurityManager);
       // 添加shiro的内置过滤器
           /*
           * anon: 无需认证就可以访问
           * authc: 认证才能访问
           * user: 必须拥有 记住我功能 才能使用
           * perms: 拥有对某个资源的权限时才能访问
           * role: 拥有某个角色权限才能访问
           * */
       // 拦截
       Map<String, String> map = new LinkedHashMap<>();

       // 无需认证就可以访问
//       map.put("/static/**","anon"); 这个过滤不了
       map.put("/css/**","anon");
       map.put("/img/**","anon");
       map.put("/js/**","anon");
       map.put("/fonts/**","anon");
       map.put("/webfonts/**","anon");

       map.put("/user/movie/getMData","anon");
       map.put("/tools/FanYi","anon");
       map.put("/tools/AddWords","anon");
       map.put("/tools/translate","anon");
       map.put("/tools/movie/mPlayer","anon");
       map.put("/toIndex","anon");


       map.put("/**","user");

       factoryBean.setFilterChainDefinitionMap(map);
//      // 设置登录页
       factoryBean.setLoginUrl("/");
       factoryBean.setLoginUrl("/login");
      // 未授权页面
       factoryBean.setUnauthorizedUrl("/noAuth");
      // 等~~~~

       return factoryBean;
  }

   // 2.创建 DefaultWebSecurityManager   权限管理，配置主要是Realm的管理认证
   @Bean(name = "securityManager")
   public DefaultWebSecurityManager defaultWebSecurityManager(
       @Qualifier("userRealm") UserRealm userRealm){
       logger.info("- - - - - - -shiro开始加载- - - - - - ");
       DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
       //关联 UserRealm
       manager.setRealm(userRealm);
       // 自定义session管理 使用redis
       manager.setSessionManager(sessionManager());
       // 自定义缓存实现 使用redis
       manager.setCacheManager(cacheManager());
       // 使用记住我
       manager.setRememberMeManager(rememberMeManager());
       return manager;
  }

   // 1.创建 realm对象    把 hashedCredentialsMatcher 注册进来~
   @Bean(name = "userRealm")
   public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher credentialsMatcher){
       UserRealm userRealm = new UserRealm();
       userRealm.setCredentialsMatcher(credentialsMatcher);
       return userRealm ;
  }
}