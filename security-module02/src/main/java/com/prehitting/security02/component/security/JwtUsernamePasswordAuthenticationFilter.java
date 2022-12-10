package com.prehitting.security02.component.security;

import cn.hutool.extra.spring.SpringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName JwtUsernamePasswordAuthenticationFilter
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/9 13:53
 * @Version 1.0
 */
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //            UmUser umUser = new ObjectMapper().readValue(request.getInputStream(), UmUser.class);
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        if (super.getAuthenticationManager()==null) {
            super.setAuthenticationManager(Optional.ofNullable(SpringUtil.getBean(AuthenticationManager.class)).orElse(new ProviderManager(new SecurityAuthenticationProvider())));
        }
        Authentication authenticate = super.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor("securejghkbgbhvcfyjvchugfugfdrydrydvgbn".getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer "+token);
    }
}
