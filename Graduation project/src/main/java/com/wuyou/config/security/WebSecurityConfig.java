package com.wuyou.config.security;

import com.wuyou.config.jwt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Description Spring Security 配置类
 * @Author 无忧
 * @Date 2022/4/3 16:07
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 自定义的Jwt Token过滤器
     * @return JwtAuthenticationTokenFilter
     * @author 无忧
     * @date 2022/4/3 17:07
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                // 自定义认证成功处理器
                .successHandler(jwtAuthenticationSuccessHandler)
                // 自定义登录失败处理器
                .failureHandler(loginFailureHandler)
                // 自定义登录拦截URI
                .loginProcessingUrl("/userLogin")
                .and().logout()
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
//                .deleteCookies("") // 登出成功处理逻辑
                .and()
                // token的验证方式不需要开启scrf的防护
                .csrf().disable()
                // 自定义认证失败类
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义权限不足处理类
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                // 设置无限状态的连接，即不创建session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .sessionManagement().maximumSessions(1)   // 限制用户登录数量
//                .and()
                .authorizeRequests()
//                .antMatchers("/static/**").permitAll()
////                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/static/**", "/userLogin/", "/login", "/validateCodeController/**").permitAll()
//                // 配置允许匿名访问的路径
                .anyRequest().authenticated();
        // 解决跨域问题
        httpSecurity.cors().and().csrf().disable();

        // 配置自己的jwt验证过滤器
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/static/**", "/userLogin/", "/login");
    }
}
