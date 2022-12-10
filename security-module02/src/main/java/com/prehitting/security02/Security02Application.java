package com.prehitting.security02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SecurityApplication
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 19:45
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {
        "com.prehitting.db",
        "com.prehitting.security02"
})
@MapperScan("com.prehitting.db.mapper")
public class Security02Application {

    public static void main(String[] args) {
        SpringApplication.run(Security02Application.class, args);
    }

}
