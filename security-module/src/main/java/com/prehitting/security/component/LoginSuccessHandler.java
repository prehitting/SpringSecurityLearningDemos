package com.prehitting.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prehitting.common.model.CommonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginSuccessHandler
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/7 22:52
 * @Version 1.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CommonResult<String> loginSuccessResult = CommonResult.success("login success");
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        String res = new ObjectMapper().writeValueAsString(loginSuccessResult);
        response.getWriter().println(res);
    }
}
