package com.njust.utils;

import com.njust.bean.baseBean.User;
import com.njust.dao.baseDao.UserMapper;

public class UserUtil {
    private final UserMapper userMapper;

    public UserUtil(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 是否重名
    public Boolean IsSameName(String userName){
        User user = new User();
        user.setUserName(userName);
        if(userMapper.selectOne(user) == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

   // 用户是否存在
    public Boolean isExist(Integer userId){
        if (userMapper.selectByPrimaryKey(userId) != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
