package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.config.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Description 自定义认证成功处理类
 * @Author 无忧
 * @Date 2022/4/3 15:06
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final String realToken = jwtTokenUtil.generateToken(authentication.getName());
        HashMap<String, Object> map = new HashMap();
        System.out.println(realToken);
        map.put("token", realToken);

        // 将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String res = JSONObject.toJSONString(Result.ok().message("登陆成功").data(map));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(res);
    }
}
