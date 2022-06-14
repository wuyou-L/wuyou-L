package com.wuyou;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.login.pojo.JwtUser;
import com.wuyou.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description
 * @Author 无忧
 * @Date 2022/4/3 15:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GraduationProjectApplication.class)
public class Test_1 {

    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void testAuthent() {
//        List<GrantedAuthority> user = AuthorityUtils.commaSeparatedStringToAuthorityList("user");
//        user.forEach(data -> {
//            System.out.println(data);
//        });


        JwtUser jwtUser = new JwtUser();
        redisUtil.setValue("a", jwtUser);
        Object a = redisUtil.getValue("a");
        System.out.println(a);
        String data = JSONObject.toJSONString(Result.ok().data("data", jwtUser));


        System.out.println(data);
    }

    @Test
    public void cookieTest() {
        String cookie = "_ga=GA1.1.425763272.1635919057; Idea-c6e2d05=a505b814-d818-4b62-ac87-8d9f85299f5a; Admin-Token=Bearer%20eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2NDk5MDQzNDU4MDMsImV4cCI6MzA4OTkwNDM0NX0.0kXSCcmy-527Xl7ldg-k9lJ2eGn7H770IKLOzanq2kEqWnXU0dM423GyLNTfjLoHnYR09Bd7FHfh5_t0a-NR5g; sidebarStatus=1";
        String token = "Bearer%20eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2NDk5MDQzNDU4MDMsImV4cCI6MzA4OTkwNDM0NX0.0kXSCcmy-527Xl7ldg-k9lJ2eGn7H770IKLOzanq2kEqWnXU0dM423GyLNTfjLoHnYR09Bd7FHfh5_t0a-NR5g";

        String substring = token.substring(6, 8);
        String[] split = token.split(substring);
        System.out.println(substring);
        System.out.println(split[0] + split[1]);
    }

    @Test
   public void dirPath(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));
    }
}
