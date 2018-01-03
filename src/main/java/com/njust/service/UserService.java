package com.njust.service;

import com.github.pagehelper.PageInfo;
import com.njust.bean.baseBean.User;

public interface UserService {

    //根据用户名获取用户信息
    public User getUserByName(String userName) throws Exception;

    //根据id获取管理员信息
    public User getManagerById(Integer userId) throws Exception;

    //添加用户
    public int insertUser(User user) throws Exception;

    //查询所有用户
    public PageInfo<User> getAll(Integer userId, Integer page, Integer pageSize) throws Exception;
}
