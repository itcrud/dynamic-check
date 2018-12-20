package com.minuor.dynamic.check;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.minuor.dynamic.check")
@MapperScan("com.minuor.dynamic.check.dao.mapper")
@EnableAspectJAutoProxy
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
