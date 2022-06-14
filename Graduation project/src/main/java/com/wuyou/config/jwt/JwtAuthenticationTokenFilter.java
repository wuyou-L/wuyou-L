package com.wuyou.config.jwt;

import com.alibaba.druid.util.StringUtils;
import com.wuyou.config.jwt.pojo.JwtProperties;
import com.wuyou.config.utils.JwtTokenUtil;
import com.wuyou.login.service.JwtUserDetailServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 自定义token过滤器
 * @Author 无忧
 * @Date 2022/4/3 15:35
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtUserDetailServiceImpl jwtUserDetailService;  // 用户登录service
    @Resource
    private JwtTokenUtil jwtTokenUtil;  // jwt 工具类
    @Resource
    private JwtProperties jwtProperties;    // jwt实体类

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //
        String requestURI = request.getRequestURI();
        String header = jwtProperties.getHeader();
        String authtoken = request.getHeader("X-Token");
//        String cookie = request.getHeader("Cookie");
        String cookieToken = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
//                cookie.getName() == "Admin-Token" &&
                if("Admin-Token".equals(cookie.getName())){
//                    System.out.println("cookie.getValue()====================" + cookie.getValue());
                    cookieToken = cookie.getValue();
                }
            }
        }
//        StringBuffer requestURL = request.getRequestURL();
//        if(requestURL.toString().toLowerCase(Locale.ROOT).endsWith("jpg") || requestURL.toString().toLowerCase(Locale.ROOT).endsWith("jpeg") || requestURL.toString().toLowerCase(Locale.ROOT).endsWith("png")){
//            System.out.println("response");
//            response.setContentType("image/jpeg");
//        }
        if (!StringUtils.isEmpty(authtoken)) {
            this.prefixCheck(authtoken, request);
        }else if(!StringUtils.isEmpty(cookieToken)){
//            String substring = cookieToken.substring(8, 2);
            String substring = cookieToken.substring(6, 8);
            String[] split = cookieToken.split(substring);
            cookieToken = split[0] + split[1];
            this.prefixCheck(cookieToken, request);
        }
//            System.out.println("authtoken: " + authtoken);

        filterChain.doFilter(request, response);
    }

    private void prefixCheck(String token, HttpServletRequest request){
        if (!StringUtils.isEmpty(token)) {
//            System.out.println("cookieToken:-------------------------------" + token);

            Boolean isExpired = jwtTokenUtil.validateToken(token);
            if (!isExpired) {
//                System.out.println(request);
                String username = jwtTokenUtil.getUsernameFromToken(token);
                System.out.println("进入自定义过滤器");
                System.out.println("当前获取用户名为：" + username);
                // 当token中的username不为空时进行验证token是否时有效的token
                if (!StringUtils.isEmpty(username)) {
                    // token中username不为空，并且Context中的认证为空，进行token验证
                    // TODO，从数据库得到带有密码的完整user信息
                    UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);

                    // username不为空，并且能够在数据库中查到用户
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        // UsernamePasswordAuthenticationToken继承AbstractAuthentication实现Authentication
                        // 所以当页面中输入用户名和密码之后首先回进入到UsernamePasswordAuthenticationToken验证(Authentication)
                        // 然后生成的Authentication会被交由AuthenticationManager来进行查看
                        // 而AuthenticationManager管理一系列的AuthenticaitonProvider
                        // 而每一个Provider都会通过User Details Service和UserDetail来发挥一个
                        // 以UsernamePasswordAuthenticationToken实现的带用户名和密码及权限的Authentication
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // 将authentication放入SecurityCOntextHolder中
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
    }
}
