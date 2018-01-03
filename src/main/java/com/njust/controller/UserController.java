package com.njust.controller;

import com.github.pagehelper.PageInfo;
import com.njust.bean.ResponseResult;
import com.njust.bean.baseBean.User;
import com.njust.service.UserService;
import com.njust.utils.ResponseResultUtil;
import com.njust.utils.SendMailUtil;
import com.sun.tools.corba.se.idl.IncludeGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    @GetMapping(value = "/getManagerById/{userId}")
    public ResponseResult<User> getManagerById(@PathVariable("userId") Integer userId) throws Exception{
        return ResponseResultUtil.success(userService.getManagerById(userId));
    }

    //管理员添加用户
    @PostMapping(value = "/add")
    public ResponseResult<User> add(User user)throws Exception{
        userService.insertUser(user);
        return ResponseResultUtil.success();
    }

    //管理员查看用户信息
    @GetMapping(value = "/getAll/{userId}")
    public ResponseResult<PageInfo<User>> getAll(@PathVariable(value = "userId") Integer userId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize) throws Exception{
        return ResponseResultUtil.success(userService.getAll(userId, page, pageSize));
    }

    //发送邮件
    @RequestMapping(value = "/sendMail")
    public ResponseResult sendMail() throws Exception {
        new SendMailUtil().sendMail();
        return ResponseResultUtil.success();
    }

    //清除缓存
    @RequestMapping(value = "/removeCache")
    public String removeCache(){
        cacheManager.getCache("baseCache").clear();
        return "缓存已清除！";
    }

}
