package com.prehitting.security02.config;

import com.google.common.base.Strings;
import com.prehitting.db.model.UmUser;
import com.prehitting.db.service.UmUserService;
import com.prehitting.security02.component.security.CommonAccessDeniedHandler;
import com.prehitting.security02.component.security.CommonAuthenticationEntryPoint;
import com.prehitting.security02.component.security.SecurityAuthenticationProvider;
import com.prehitting.security02.model.UmUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 19:46
 * @Version 1.0
 */
@Order(1)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CommonAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CommonAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .headers().frameOptions().disable().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()
                .disable();
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/login","/register").permitAll()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
//        http.addFilter(new JwtUsernamePasswordAuthenticationFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UmUserService umUserService;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            // get user basic info by username
            UmUser umUser = umUserService.getUserByUsername(username);
            if (Strings.isNullOrEmpty(umUser.getUsername())) {
                throw new UsernameNotFoundException("user : "+ username + "not found");
            }
            // get list of authorities by uid
            Set<SimpleGrantedAuthority> allPermissions = umUserService.getAllPermissions(umUser.getUid())
                    .stream()
                    .map(umPermission -> new SimpleGrantedAuthority(umPermission.getPermissionName()))
                    .collect(Collectors.toSet());
            return new UmUserDetails(umUser, allPermissions);
        };
    }

    @Autowired
    private SecurityAuthenticationProvider securityAuthenticationProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(securityAuthenticationProvider);
    }

}
