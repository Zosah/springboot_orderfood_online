package com.guzhz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guzhz.mapper")
public class SpringbootOrderfoodOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOrderfoodOnlineApplication.class, args);
    }

}
