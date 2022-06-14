package com.wuyou.validateCode.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ValidateCodeService {


    public String getCaptcha(HttpServletRequest request, HttpServletResponse response);
    public boolean checkingCode( String validateCode, String code);
}
