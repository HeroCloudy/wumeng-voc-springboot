package com.wumeng.voc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.wumeng.voc.mapper")
@ComponentScan(basePackages = "com.wumeng")
@SpringBootApplication
public class VocApplication {

    public static void main(String[] args) {
        SpringApplication.run(VocApplication.class, args);
    }
}
