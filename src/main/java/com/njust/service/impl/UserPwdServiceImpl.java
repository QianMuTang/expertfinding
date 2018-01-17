package com.njust.service.impl;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.UserPwd;
import com.njust.dao.baseDao.UserPwdMapper;
import com.njust.service.UserPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPwdServiceImpl implements UserPwdService {

    @Autowired
    UserPwdMapper userPwdMapper;

    @Override
    public String getPwdById(Integer userId) throws Exception {
        try {
            return userPwdMapper.selectByPrimaryKey(userId).getPassword();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    public int modifyPwd(Integer userId, String password_old, String password_one, String password_two, Integer priv) throws Exception {
        //是否输入旧密码
        if (password_old.trim()==null || password_old.trim()==""){
            throw new CustomException(ResponseResultEnum.MISSING_OLD_PWD);
        }
        //是否输入新密码
        if (password_one.trim()==null || password_one.trim()=="" || password_two.trim()!=null || password_two.trim()!=""){
            throw new CustomException(ResponseResultEnum.MISSING_NEW_PWD);
        }
        //两次输入密码是否一致
        if (!password_one.trim().equals(password_two.trim())){
            throw new CustomException(ResponseResultEnum.NOT_SAME_PWD);
        }

        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(userId);
        userPwd.setPassword(password_old);
        UserPwd userPwd_new;
        try{
            userPwd_new = userPwdMapper.selectOne(userPwd);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
        //校验旧密码输入是否正确
        if (userPwd_new==null){
            throw new CustomException(ResponseResultEnum.PASSWORD_ERROR);
        }
        //更新密码
        try{
            userPwd_new.setPassword(password_one);
            userPwdMapper.updateByPrimaryKey(userPwd_new);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
        return 0;
    }
}