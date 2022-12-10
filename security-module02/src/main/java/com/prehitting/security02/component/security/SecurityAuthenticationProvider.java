package com.prehitting.security02.component.security;

import com.prehitting.db.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @ClassName SecurityAuthenticationProvider
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/9 17:58
 * @Version 1.0
 */
@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UmUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String password = (String) token.getCredentials();
        String username = (String) token.getPrincipal();
        if (password.equals(userService.getUserByUsername(username).getPassword())) {
            return token;
        }
        throw new BadCredentialsException("bad password");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
