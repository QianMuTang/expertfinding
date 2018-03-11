package com.njust.service.impl;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.UserPwd;
import com.njust.dao.baseDao.UserPwdMapper;
import com.njust.service.UserPwdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPwdServiceImpl implements UserPwdService {
    private final static Logger logger = LoggerFactory.getLogger(UserPwdServiceImpl.class);

    @Autowired
    UserPwdMapper userPwdMapper;

    @Override
    public String getPwdById(Integer userId) throws Exception {
        try {
            UserPwd userPwd = new UserPwd();
            userPwd.setUserId(userId);
            return userPwdMapper.selectOne(userPwd).getPassword();
        } catch (Exception e) {
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    public int modifyPwd(Integer userId, String password_one, String password_two, Integer priv) throws Exception {
        //是否输入新密码
        if (password_one.trim().equals("") || password_two.trim().equals("")){
            throw new CustomException(ResponseResultEnum.MISSING_NEW_PWD);
        }
        //两次输入密码是否一致
        if (!password_one.trim().equals(password_two.trim())){
            throw new CustomException(ResponseResultEnum.NOT_SAME_PWD);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(userId);

        //更新密码
        try{
            userPwd = userPwdMapper.selectOne(userPwd);
            userPwd.setPassword(encoder.encode(password_one.trim()));
            userPwdMapper.updateByPrimaryKey(userPwd);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
        return 0;
    }
}