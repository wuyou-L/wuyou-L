package com.wuyou.login.service;

import com.alibaba.druid.util.StringUtils;
import com.wuyou.login.pojo.JwtUser;
import com.wuyou.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 查询用户信息
 * @Author 无忧
 * @Date 2022/4/3 15:25
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username：" + username);
        if (!StringUtils.isEmpty(username)) {

            JwtUser jwtUser = userMapper.loadByUsername(username);
            if (jwtUser == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            return jwtUser;
        }
        return null;
    }

    public JwtUser getUserInfo(String username){
        JwtUser jwtUser = userMapper.loadByUsername(username);
        if (jwtUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<String> roleList = new ArrayList<>();
        roleList.add(jwtUser.getRoleCode());
        jwtUser.setRoles(roleList);
        return jwtUser;
    }
}
