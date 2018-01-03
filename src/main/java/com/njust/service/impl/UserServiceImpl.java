package com.njust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.dao.UserMapperExtend;
import com.njust.service.UserService;
import com.njust.utils.IsManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "baseCache")
public class UserServiceImpl implements UserService{

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapperExtend userMapperExtend;

    @Override
    public User getUserByName(String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        try{
            return userMapperExtend.selectOne(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    @Cacheable
    public User getManagerById(Integer userId) throws Exception {
        User user = new User();
        user.setUserId(userId);
        user.setPrivLevel(1);
        try{
            return userMapperExtend.selectOne(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    public int insertUser(User user) throws Exception {
        try{
            userMapperExtend.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.INSERT_ERROR);
        }
        return 1;
    }

    @Override
    @Cacheable
    public PageInfo<User> getAll(Integer userId, Integer page, Integer pageSize) throws Exception{
        try{
            //是否为管理员
            if (new IsManagerUtil(userMapperExtend).isManager(userId)){
                PageHelper.startPage(page, pageSize);
                PageInfo<User> pageInfo = new PageInfo<User>(userMapperExtend.selectAll());
                return pageInfo;
            }
            else{
                throw new CustomException(ResponseResultEnum.NOT_MANAGER);
            }
        }catch (Exception e){
            e.printStackTrace();
            if (e instanceof CustomException){
                throw e;
            }
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }
}
