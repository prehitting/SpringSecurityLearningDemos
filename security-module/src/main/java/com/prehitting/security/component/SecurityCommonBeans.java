package com.prehitting.security.component;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName SecurityCommonBeans
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/6 18:56
 * @Version 1.0
 */
@Component
public class SecurityCommonBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
