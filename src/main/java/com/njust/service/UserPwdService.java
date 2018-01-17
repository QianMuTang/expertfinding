package com.njust.service;

public interface UserPwdService {

    //根据用户id查询密码
    String getPwdById(Integer userId) throws Exception;

    //修改用户密码
    int modifyPwd(Integer userId, String password_old, String password_one, String password_two, Integer priv) throws Exception;
}
