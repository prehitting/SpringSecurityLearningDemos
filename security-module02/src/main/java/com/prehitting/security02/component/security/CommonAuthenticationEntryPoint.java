package com.prehitting.security02.component.security;

import cn.hutool.json.JSONUtil;
import com.prehitting.common.model.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CommonAuthenticationEntryPoint
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 23:12
 * @Version 1.0
 */
@Component
public class CommonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse("fail -> "+CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
