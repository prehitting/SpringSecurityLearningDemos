package com.prehitting.security02.component.security;

import cn.hutool.json.JSONUtil;
import com.prehitting.common.model.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CommonAccessDeniedHandler
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/8 23:01
 * @Version 1.0
 */
@Component
public class CommonAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden("Fail -> "+e.getMessage())));
        response.getWriter().flush();
    }
}
