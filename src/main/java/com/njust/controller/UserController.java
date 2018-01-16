package com.njust.controller;

import com.github.pagehelper.PageInfo;
import com.njust.bean.CustomException;
import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.bean.baseBean.UserPwd;
import com.njust.service.UserPwdService;
import com.njust.service.UserService;
import com.njust.utils.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 简单的RESTful API
 */
@RestController
@RequestMapping("admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPwdService userPwdService;

    //管理员通过id查询一个用户
    @GetMapping(value = "/{userId}")
    public ResponseResult<User> getUserById(@PathVariable("userId") Integer userId) throws Exception{
        //管理员只能查询普通用户
        return ResponseResultUtil.success(userService.getUserById(userId, 3));
    }

    //管理员添加用户（添加用户名、密码、是否推送）
    @PostMapping
    public ResponseResult<User> insert(User user, @RequestParam(value = "password", required = true) String password)throws Exception{
        //管理员只能添加普通用户
        user.setPrivLevel(3);
        userService.insertUser(user, password);
        return ResponseResultUtil.success();
    }

    //管理员获取用户列表
    @GetMapping
    public ResponseResult<PageInfo<User>> getAll(@RequestParam(value = "order", required = false,defaultValue = "user_id") String order,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
                                                 User user) throws Exception{
        user.setPrivLevel(3);
        return ResponseResultUtil.success(userService.getAll(order, page, pageSize, user));
    }

    //管理员通过id更新一个用户（只更新用户名和是否推送）
    @PutMapping(value = "/{userId}")
    public ResponseResult<User> updateUserById(@PathVariable("userId") Integer userId, User user,
                                                @RequestParam(value = "password", required = false)String password) throws Exception{
        user.setUserId(userId);
        //管理员只能更新普通用户
        user.setPrivLevel(3);
        //更新用户属性和密码
        userService.updateUser(user, password);
        return ResponseResultUtil.success();
    }

    //管理员通过id删除一个用户
    @DeleteMapping(value = "/{userId}")
    public ResponseResult<User> deleteUserById(@PathVariable("userId") String userIds) throws Exception{
        String[] userIdArray = userIds.split("&");
        for (String userId : userIdArray){
            userService.deleteUser(Integer.parseInt(userId));
        }
        return ResponseResultUtil.success();
    }
}
