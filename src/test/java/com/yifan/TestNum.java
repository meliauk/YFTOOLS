package com.yifan;

import com.yifan.entity.User;
import com.yifan.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestNum {

    /*
    * 告诉你我注入了bean就好了啊。所以在mapper或者dao上使用注解
    * @Component或者@Repository来告诉spring我已经把这个bean注入进来了
    *
    * */

    @Autowired
    private UserMapper userMapper ;


    @Test
    void num(){
        List<User> listUser = userMapper.getListUser();
        listUser.forEach(System.err::print);

    }

}
