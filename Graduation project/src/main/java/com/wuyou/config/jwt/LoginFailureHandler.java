package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.base.resp.impl.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*自定义认证失败处理类
*@author 无忧
*@date 2022/4/3 14:39
*/
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("认证失败");
        returnFailure(response);
    }

    public void returnFailure(HttpServletResponse response) throws IOException{
        String s = JSONObject.toJSONString(Result.error().code(ResultCode.ERROR_AUTHENTICATION.getCode()).message(ResultCode.ERROR_AUTHENTICATION.getMessage()));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
