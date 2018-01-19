package com.njust.controller;

import com.njust.bean.ResponseResult;
import com.njust.bean.baseBean.User;
import com.njust.service.UserPwdService;
import com.njust.service.UserService;
import com.njust.utils.ResponseResultUtil;
import com.njust.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通用户、管理员、超级管理员接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPwdService userPwdService;

    /**
     * 下面是普通用户接口
     */
    //用户注册
    @PostMapping(value = "/register")
    public ResponseResult register(User user, @RequestParam(value = "password") String password)throws Exception{
        user.setPrivLevel(3);
        userService.insertUser(user, password);
        return ResponseResultUtil.success();
    }

    //用户修改个人信息
    @GetMapping(value = "/user/name")
    public ResponseResult updateUserName(User user) throws Exception{
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        user.setUserId(userId);
        user.setPrivLevel(3);
        userService.updateUser(user, "");
        return ResponseResultUtil.success();
    }

    //用户修改密码
    @GetMapping(value = "/user/pwd")
    public ResponseResult updateUserPwd(@RequestParam(value = "pwd_one")String password_one,
                                                 @RequestParam(value = "pwd_two") String password_two) throws Exception {
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        userPwdService.modifyPwd(userId, password_one, password_two, 3);
        return ResponseResultUtil.success();
    }

    /**
     * 下面是管理员接口
     */
    //管理员通过id查询一个用户
    @GetMapping(value = "/admin/{userId}")
    public ResponseResult getUserById(@PathVariable("userId") Integer userId) throws Exception{
        //管理员只能查询普通用户
        return ResponseResultUtil.success(userService.getUserById(userId, 3));
    }

    //管理员添加用户（添加用户名、密码、是否推送）
    @PostMapping(value = "/admin")
    public ResponseResult insert(User user, @RequestParam(value = "password") String password)throws Exception{
        //管理员只能添加普通用户
        user.setPrivLevel(3);
        userService.insertUser(user, password);
        return ResponseResultUtil.success();
    }

    //管理员获取用户列表
    @GetMapping(value = "/admin")
    public ResponseResult getAll(@RequestParam(value = "order", required = false,defaultValue = "user_id") String order,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
                                                 User user) throws Exception{
        user.setPrivLevel(3);
        return ResponseResultUtil.success(userService.getAll(order, page, pageSize, user));
    }

    //管理员通过id更新一个用户（只更新用户名/是否推送/密码）
    @PutMapping(value = "/admin/{userId}")
    public ResponseResult updateUserById(@PathVariable("userId") Integer userId, User user,
                                         @RequestParam(value = "password", required = false)String password) throws Exception{
        user.setUserId(userId);
        //管理员只能更新普通用户
        user.setPrivLevel(3);
        //更新用户属性和密码
        userService.updateUser(user, password);
        return ResponseResultUtil.success();
    }

    //管理员通过id删除一个用户
    @DeleteMapping(value = "/admin/{userId}")
    public ResponseResult deleteUserById(@PathVariable("userId") String userIds) throws Exception{
        //删除普通用户，返回成功删除的个数
        return ResponseResultUtil.success(userService.deleteUser(userIds, 3));
    }

    //管理员修改昵称
    @GetMapping(value = "/admin/name")
    public ResponseResult updateAdminName(@RequestParam(value = "userName") String userName) throws Exception{
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPrivLevel(2);
        userService.updateUser(user, "");
        return ResponseResultUtil.success();
    }

    //管理员修改密码
    @GetMapping(value = "/admin/pwd")
    public ResponseResult updateAdminPwd(@RequestParam(value = "pwd_one")String password_one,
                                                  @RequestParam(value = "pwd_two") String password_two) throws Exception {
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        userPwdService.modifyPwd(userId, password_one, password_two, 2);
        return ResponseResultUtil.success();
    }

    /**
     * 下面是超级管理员接口
     */
    //超级管理员通过id查询一个管理员
    @GetMapping(value = "/super/{userId}")
    public ResponseResult getAdminById(@PathVariable("userId") Integer userId) throws Exception{
        //超级管理员只能查询管理员
        return ResponseResultUtil.success(userService.getUserById(userId, 2));
    }

    //超级管理员添加管理员（添加用户名、密码）
    @PostMapping(value = "/super")
    public ResponseResult insertAdmin(User user, @RequestParam(value = "password") String password)throws Exception{
        //超级管理员只能添加管理员
        user.setPrivLevel(2);
        userService.insertUser(user, password);
        return ResponseResultUtil.success();
    }

    //超级管理员获取管理员列表
    @GetMapping(value = "/super")
    public ResponseResult getAllAdmin(@RequestParam(value = "order", required = false,defaultValue = "user_id") String order,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
                                      User user) throws Exception{
        user.setPrivLevel(2);
        return ResponseResultUtil.success(userService.getAll(order, page, pageSize, user));
    }

    //超级管理员通过id更新一个管理员（只更新用户名/是否推送/密码）
    @PutMapping(value = "/super/{userId}")
    public ResponseResult updateAdminById(@PathVariable("userId") Integer userId, User user,
                                                   @RequestParam(value = "password", required = false)String password) throws Exception{
        user.setUserId(userId);
        //超级管理员只能更新管理员
        user.setPrivLevel(2);
        //更新管理员属性和密码
        userService.updateUser(user, password);
        return ResponseResultUtil.success();
    }

    //管理员通过id删除一个用户
    @DeleteMapping(value = "/super/{userId}")
    public ResponseResult deleteAdminById(@PathVariable("userId") String userIds) throws Exception{
        //删除管理员，返回成功删除的个数
        return ResponseResultUtil.success(userService.deleteUser(userIds, 2));
    }

    //超级管理员修改昵称
    @GetMapping(value = "/super/name")
    public ResponseResult updateSuperName(@RequestParam(value = "userName") String userName) throws Exception{
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPrivLevel(1);
        userService.updateUser(user, "");
        return ResponseResultUtil.success();
    }

    //超级管理员修改密码
    @GetMapping(value = "/super/pwd")
    public ResponseResult updateSuperPwd(@RequestParam(value = "pwd_one")String password_one,
                                                  @RequestParam(value = "pwd_two") String password_two) throws Exception {
        Integer userId = Integer.parseInt(UserUtil.getLoginId().getData().toString());
        userPwdService.modifyPwd(userId, password_one, password_two, 1);
        return ResponseResultUtil.success();
    }
}
