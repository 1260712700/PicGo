package com.jp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan("com.jp.mapper")
@EnableMethodSecurity
public class JChatServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JChatServerApplication.class, args);
    }

}
