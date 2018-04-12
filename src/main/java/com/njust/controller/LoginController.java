package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.config.security.UserInfo;
import com.njust.service.UserService;
import com.njust.utils.ResponseResultUtil;
import com.njust.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于测试授权认证以及权限分配，权限配置在yml文件修改测试
 */
@Controller
public class LoginController {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    //wdd页面路由
    @RequestMapping("/expert/detail")
    public String expertDetail(){
        return "expert_detail";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/userindex")
    public String userindex(){
        return "userindex";
    }

    @RequestMapping("/manager")
    public String manager(){
        return "manager";
    }

    @RequestMapping("/supermanager")
    public String supermanager(){
        return "supermanager";
    }












    //用户注册
    @PostMapping("/api/register")
    @ResponseBody
    public ResponseResult register(User user, @RequestParam(value = "password") String password)throws Exception{
        user.setPrivLevel(3);
        userService.insertUser(user, password);
        return ResponseResultUtil.success();
    }

    @RequestMapping("/login_page")
    @ResponseBody
    public ResponseResult loginPage(){
        return ResponseResultUtil.error(ResponseResultEnum.NO_PERMISSION);
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

    @RequestMapping("/api/logout")
    @ResponseBody
    public ResponseResult logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return ResponseResultUtil.success();
        }else {
            return ResponseResultUtil.error(ResponseResultEnum.LOGOUT_FAIL);
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
    @RequestMapping("/api/removeCache")
    @ResponseBody
    public String removeCache(){
        cacheManager.getCache("baseCache").clear();
        return "缓存已清除！";
    }
}
