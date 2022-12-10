package com.prehitting.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName SecurityApplication
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/5 19:30
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {
        "com.prehitting.security",
        "com.prehitting.db"
})
//@MapperScan("com.prehitting.db.mapper")
public class SecurityApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);

    }
}
