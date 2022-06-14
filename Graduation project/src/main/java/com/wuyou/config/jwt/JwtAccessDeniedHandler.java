package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.base.resp.impl.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 自定义无权访问处理类
* @author 无忧
* @date 2022/4/3 14:40
*/
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("权限不足");
        String s = JSONObject.toJSONString(Result.error().code(ResultCode.NO_ROLE.getCode()).message(ResultCode.NO_ROLE.getMessage()));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
