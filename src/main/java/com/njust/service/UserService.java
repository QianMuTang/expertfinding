package com.njust.service;

import com.github.pagehelper.PageInfo;
import com.njust.bean.baseBean.User;

public interface UserService {

    //根据id和权限获取用户信息
    public User getUserById(Integer userId, Integer priv) throws Exception;

    //添加用户
    public int insertUser(User user) throws Exception;

    //根据权限和排序方式查询所有用户
    public PageInfo<User> getAll(String order, Integer page, Integer pageSize, User user) throws Exception;

    //根据id更新用户信息
    public int updateUser(User user) throws Exception;

    //根据id删除用户
    public int deleteUser(Integer userId) throws Exception;

    //根据用户名获取用户信息
    public User getUserByName(String userName) throws Exception;
}
