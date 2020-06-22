package com.yifan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yifan.mapper")
public class YftoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YftoolsApplication.class, args);
    }

}
