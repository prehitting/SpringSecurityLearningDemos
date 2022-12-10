package com.prehitting.security.config;

import com.prehitting.security.component.ApplicationRole;
import com.prehitting.security.component.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/5 21:37
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Resource(type = PasswordEncoder.class)
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/admin/all")
//                .hasAnyRole(ApplicationRole.ADMIN.getRoleName())
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .and()
                .httpBasic();
//        http.csrf()
//                .disable();
//        http.exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)
//                .authenticationEntryPoint(authenticationEntryPoint);
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("root")
                .password(passwordEncoder.encode("root"))
//                .roles(ApplicationRole.USER.getRoleName())
                .authorities(ApplicationRole.USER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
