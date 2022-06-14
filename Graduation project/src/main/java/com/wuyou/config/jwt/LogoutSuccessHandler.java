package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 退出登录
 * @Author 无忧
 * @Date 2022/4/4 22:11
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(Result.ok().message("退出登录")));
    }
}
