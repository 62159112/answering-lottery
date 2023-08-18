package com.example.mytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mytest.mapper")
public class MyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTestApplication.class, args);
    }

}
