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

import com.lifeng.dao.UserDao;
import com.lifeng.service.RegisterService;
import com.sun.deploy.net.HttpResponse;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
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
        return "true";
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(HttpSession session, HttpServletResponse response,String username) {
        System.out.println(username+"\n");
        User user=registerService.checkUser(username);
        if(user==null){
            return "true";
        }
        else {
            System.out.println(user.getUsername()+"\n");
            return "false";
        }
    }

    @RequestMapping("/searchUser")
    @ResponseBody
    public Object searchUser(HttpSession session,HttpServletResponse response,String type,String val){
        User user;
        Map<String,Object> map = new HashMap<String,Object>();
        //System.out.println("username".equals(type)+"\t"+val);
        if("username".equals(type)){
            user=registerService.lookUserInName(val);
            if(user!=null)
            {
                map.put("state","true");
            }
            else map.put("state","false");
        }
        else {
            int newValue =Integer.parseInt(val);
            user=registerService.lookUserInId(newValue);
            if(user!=null)
                map.put("state","true");
            else return map;
        }
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("name",user.getName());
        map.put("gender",user.getGender());
        map.put("email",user.getEmail());
        map.put("telephone",user.getTelephone());
        map.put("role",user.getRole());
        map.put("introduce",user.getIntroduce());
        map.put("userstate","1");
        map.put("regtime",user.getRegistTime());
        map.put("address",user.getShippingAddress());
        return map;
    }

    @RequestMapping("/DeleteUser")
    @ResponseBody
    public Object deleteUser(HttpSession session,HttpServletResponse response,String type,String id){
        Map<String,Object> map = new HashMap<String,Object>();
        //System.out.println(type+"\t"+id);
        int intId=Integer.parseInt(id);
        //删除操作
        if("mod".equals(type)){
            int changeStatus = registerService.deleteUser(intId);
            if(changeStatus!=0){
                map.put("state","true");
                return map;
            }
            else map.put("state","false");
        }
        return map;
    }

    @RequestMapping("/ModifyUser")
    @ResponseBody
    public Object ModifyUser(HttpSession session,HttpServletResponse response,User user){
        Map<String,Object> map = new HashMap<String,Object>();
        int changeStatus = registerService.modifiablevariable(user);
        System.out.println(changeStatus);
        if(changeStatus !=0){
            map.put("state","true");
            return map;
        }
        map.put("state" , "false");
        return map;
    }
}
