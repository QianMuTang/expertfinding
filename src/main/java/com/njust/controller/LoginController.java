package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.config.security.UserInfo;
import com.njust.utils.ResponseResultUtil;
import com.njust.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于测试授权认证以及权限分配，权限配置在yml文件修改测试
 */
@Controller
public class LoginController {
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/api/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/api/loginName")
    @ResponseBody
    public ResponseResult loginName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserInfo) {
            return ResponseResultUtil.success(((UserInfo)principal).getUsername());
        } else {
            return ResponseResultUtil.error(ResponseResultEnum.NOT_LOGIN);
        }
    }

    /**
     * 无效
     * @return
     * @throws Exception
     */
    //发送邮件
    @RequestMapping(value = "/api/sendMail")
    public ResponseResult sendMail() throws Exception {
        new SendMailUtil().sendMail();
        return ResponseResultUtil.success();
    }

    //清除缓存
    @RequestMapping(value = "/api/removeCache")
    public String removeCache(){
        cacheManager.getCache("baseCache").clear();
        return "缓存已清除！";
    }
}
