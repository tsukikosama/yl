package com.example.yl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//配置包扫描
@MapperScan("com.example.yl.mapper")
public class YlApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlApplication.class, args);
    }

}
