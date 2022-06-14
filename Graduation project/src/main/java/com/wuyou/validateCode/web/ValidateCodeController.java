package com.wuyou.validateCode.web;

import com.alibaba.fastjson.JSONObject;
import com.wuyou.base.resp.Result;
import com.wuyou.utils.RedisUtil;
import com.wuyou.validateCode.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/validateCodeController")
@CrossOrigin
public class ValidateCodeController {


    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private RedisUtil redisUtil;
    // 生成验证码图片
    @RequestMapping("/getCaptchaImage/{param}")
    public Object getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("code", "");
            String captcha = validateCodeService.getCaptcha(request, response);
            redisUtil.setValue("RANDOMKEY", captcha);
//            ValidateCode validateCode = new ValidateCode();
//
//            // 直接返回图片
//            validateCode.getRandomCodeImage(request, response);
//            validateCodeService.getCaptcha(request, response);
            return captcha;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    @RequestMapping("/checkingCode")
    public Object checkingCode(@RequestBody JSONObject validateCode, HttpServletRequest request) {
        HttpSession session = request.getSession();

        System.out.println("sessionCheck => " + session.getId());
        String attribute = (String) session.getAttribute("RANDOMKEY");
        if (attribute == null) {
            attribute = (String) redisUtil.getValue("RANDOMKEY");
        }
        String o = (String) validateCode.get("validateCode");
//        System.out.println(attribute);
        System.out.println(validateCode);
        try {
            boolean b = validateCodeService.checkingCode(o, attribute);
            System.out.println(attribute.equals(o));
            System.out.println(attribute == o);
            return Result.ok().data("data", b);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().code(500).message("操作失败");
        }
    }
}
