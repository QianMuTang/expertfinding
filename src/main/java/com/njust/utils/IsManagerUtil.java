package com.njust.utils;

import com.njust.bean.baseBean.User;
import com.njust.dao.UserMapperExtend;

public class IsManagerUtil {
    final UserMapperExtend userMapperExtend;

    public IsManagerUtil(UserMapperExtend userMapperExtend) {
        this.userMapperExtend = userMapperExtend;
    }

    public Boolean isManager(Integer userId){
        User user = new User();
        user.setUserId(userId);
        if (userMapperExtend.selectOne(user).getPrivLevel() == 1){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
