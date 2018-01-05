package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.utils.ResponseResultUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//    @RequestMapping("/login-error")
//    public String loginError(){
//        return "login-error";
//    }

    @RequestMapping("/whoim")
    @ResponseBody
    public ResponseResult whoIm(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ResponseResultUtil.success(((UserDetails)principal).getUsername());
        } else {
            return ResponseResultUtil.error(ResponseResultEnum.NOT_LOGIN);
        }
    }
}
