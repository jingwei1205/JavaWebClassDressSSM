package com.lifeng.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lifeng.service.RegisterService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.User;
import com.lifeng.service.UserService;
import com.lifeng.util.Unicode;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    public RegisterService getRegisterService(){return registerService;}
    public void setRegisterService(RegisterService registerService){this.registerService=registerService;}

    @RequestMapping("/regist")
    @ResponseBody
    public String regist(HttpSession session, HttpServletResponse response, User user) {
        registerService.regist(user);
        Map<String, Object> map = new HashMap<String, Object>();
        String t="true";
        return t;
    }
}
