package com.gavin.itnews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@MapperScan(basePackages = "com.gavin.itnews.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ItNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItNewsApplication.class, args);
    }

}
