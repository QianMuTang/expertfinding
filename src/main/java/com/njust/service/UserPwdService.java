package com.njust.service;

import com.njust.bean.baseBean.UserPwd;

public interface UserPwdService {

    //根据用户id查询密码
    String getPwdById(Integer userId) throws Exception;
}
