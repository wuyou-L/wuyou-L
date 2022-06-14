package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.base.resp.impl.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * @param request
     * @param response
     * @param authException
     * @author 无忧
     * @date 2022/4/3 11:38
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("无凭证");
        String s = JSONObject.toJSONString(Result.error().code(ResultCode.NO_PERMISSION.getCode()).message(ResultCode.NO_PERMISSION.getMessage()));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
