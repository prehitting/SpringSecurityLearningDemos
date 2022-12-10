package com.prehitting.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName DbApplication
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/7 9:04
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.prehitting.db")
public class DbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }


}
