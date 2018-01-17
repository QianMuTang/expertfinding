package com.njust.service;

import com.github.pagehelper.PageInfo;
import com.njust.bean.baseBean.User;

public interface UserService {

    //根据id和权限获取用户信息
    User getUserById(Integer userId, Integer priv) throws Exception;

    //添加用户
    int insertUser(User user, String password) throws Exception;

    //根据权限和排序方式查询所有用户
    PageInfo<User> getAll(String order, Integer page, Integer pageSize, User user) throws Exception;

    //根据id更新用户信息
    int updateUser(User user, String password) throws Exception;

    //根据id删除用户
    int deleteUser(String userIds, Integer priv) throws Exception;

    //根据用户名获取用户信息
    User getUserByName(String userName) throws Exception;

    //更新用户名
    int updateName(Integer userId, String userName) throws Exception;
}
