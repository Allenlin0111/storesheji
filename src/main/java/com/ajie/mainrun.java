package com.ajie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序的入口
 * @author ajie
 */
@MapperScan("com.ajie.mapper")
@SpringBootApplication
public class mainrun {

    public static void main(String[] args) {
        SpringApplication.run(mainrun.class, args);
    }

}
