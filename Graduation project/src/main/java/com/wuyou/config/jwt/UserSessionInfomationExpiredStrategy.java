package com.wuyou.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 会话信息过期策略
 * @Author 无忧
 * @Date 2022/4/4 22:19
 */
@Component
public class UserSessionInfomationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(Result.ok().message("账号下线")));
    }
}
