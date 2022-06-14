package com.wuyou.validateCode.service.impl;

import com.wuyou.validateCode.service.ValidateCodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {


    @Override
    public String getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        ValidateCode validateCode = new ValidateCode();

        // 直接返回图片
        return validateCode.getRandomCodeImage(request, response);
    }

    @Override
    public boolean checkingCode(String validateCode, String code) {
        return validateCode.equals(code) || code == validateCode;
    }

}
