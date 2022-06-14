package com.wuyou.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.config.utils.JwtTokenUtil;
import com.wuyou.login.pojo.JwtUser;
import com.wuyou.login.service.JwtUserDetailServiceImpl;
import com.wuyou.user.bean.UserBean;
import com.wuyou.user.service.UserService;
import com.wuyou.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 登录controller
 * @Author 无忧
 * @Date 2022/4/3 17:20
 */
@RestController
@RequestMapping("/userLogin")
//@CrossOrigin    // 解决跨域
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Object login(@RequestBody JwtUser jwtUser){
        UserDetails userDetails = null;
        if (jwtUser.getUsername() == null) {
            return Result.error().message("用户名不能为空！");
        }
        try {
            userDetails = jwtUserDetailService.loadUserByUsername(jwtUser.getUsername());
            if (userDetails == null) {
                return Result.error().message("用户不存在，登录失败！");
            }else {
                UserBean userBean = new UserBean();
                userBean.setUser_account(jwtUser.getUsername());
                userBean.setUser_pwd(jwtUser.getPassword());
                UserBean userBean1 = userService.querySingleUser(userBean);
                if (userBean1 == null) {
                    return Result.error().message("密码错误，登录失败！");
                }
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return Result.error().message("用户不存在，登录失败！");
        }
        String token = jwtTokenUtil.generateToken(jwtUser.getUsername());
        return Result.ok().data("token", token).data("pwd", jwtUser.getPassword());
    }


    @PreAuthorize("hasAuthority('ROLE_admin')")
    @PostMapping("/test")
    public Object test(){
        System.out.println("test");
        return Result.ok();
    }

    @GetMapping("/userInfo")
    public Object userInfo(HttpServletRequest request){
        String token = request.getHeader("X-Token");

        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserBean userBean = new UserBean();
        userBean.setUser_account(username);
        UserBean userBean1 = userService.querySingleUser(userBean);
        System.out.println(userBean1.toString());
        JwtUser userInfo = jwtUserDetailService.getUserInfo(username);
        System.out.println(username);
        redisUtil.setValue(userBean1.getUser_account() + "", JSONObject.toJSON(userBean1).toString());
        return Result.ok().data("name", userInfo.getUsername()).data("roles", userInfo.getRoles()).data("token", token).data("avatar", userBean1.getUser_img_name()).data("userInfo", userBean1);
    }

    @PostMapping("/logout")
    public Object logout(HttpServletRequest request){
        String token = request.getHeader("X-Token");

//        System.out.println("123");
//        List<String> strings = new ArrayList<>();
//        strings.add("001");
        return Result.ok().message("退出登录！");
    }
}
