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
}