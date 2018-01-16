package com.njust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.bean.baseBean.UserPwd;
import com.njust.bean.baseBean.UserRole;
import com.njust.dao.baseDao.UserMapper;
import com.njust.dao.baseDao.UserPwdMapper;
import com.njust.dao.baseDao.UserRoleMapper;
import com.njust.service.UserService;
import com.njust.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "baseCache")
public class UserServiceImpl implements UserService{

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserPwdMapper userPwdMapper;

    @Override
    @Cacheable
    public User getUserById(Integer userId, Integer priv) throws Exception {
        User user = new User();
        user.setUserId(userId);
        user.setPrivLevel(priv);
        try{
            return userMapper.selectOne(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user, String password) throws Exception {
        //数据是否缺失
        if (user.getUserName()!=null && user.getUserName()!="" && user.getIsPush()!=null && password!=null && password!=""){
            try{
                //判断用户名是否重复
                if(!new UserUtil(userMapper).IsSameName(user.getUserName())){
                    throw new CustomException(ResponseResultEnum.SAME_NAME);
                }
                //密码加密
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            user.setPassword(encoder.encode(user.getPassword().trim()));
                //插入用户信息
                userMapper.insertSelective(user);
                //获取插入用户的id
                Integer userId = userMapper.selectOne(user).getUserId();
                //插入用户密码
                UserPwd userPwd = new UserPwd();
                userPwd.setPassword(password);
                userPwd.setUserId(userId);
                userPwdMapper.insert(userPwd);
                //插入用户角色
                UserRole userRole = new UserRole();
                userRole.setUid(userId);
                userRole.setRid(user.getPrivLevel());
                return userRoleMapper.insert(userRole);
            }catch (Exception e){
                e.printStackTrace();
                if (e instanceof CustomException){
                    throw e;
                }
                throw new CustomException(ResponseResultEnum.INSERT_ERROR);
            }
        }else{
            throw new CustomException(ResponseResultEnum.MISSING_DATA);
        }
    }

    @Override
    @Cacheable
    public PageInfo<User> getAll(String order, Integer page, Integer pageSize, User user) throws Exception{
        try{
            PageHelper.startPage(page, pageSize, order);
            PageInfo<User> pageInfo = new PageInfo<>(userMapper.select(user));
            return pageInfo;
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user, String password) throws Exception {
        try {
            if (user.getUserName()!=null && user.getUserName()!="" && user.getIsPush()!=null){
                userMapper.updateByPrimaryKey(user);
            }
            if(password!=null && password!=""){
                logger.info("密码：{}",password);
                UserPwd userPwd = new UserPwd();
                userPwd.setUserId(user.getUserId());
                Integer pwdId = userPwdMapper.selectOne(userPwd).getId();
                userPwd.setId(pwdId);
                userPwd.setPassword(password);
                userPwdMapper.updateByPrimaryKeySelective(userPwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
        return 1;
    }

    @Override
    public int deleteUser(Integer userId) throws Exception {
        //用户是否存在
        if (new UserUtil(userMapper).isExist(userId)){
            try {
                return userMapper.deleteByPrimaryKey(userId);
            }catch (Exception e){
                e.printStackTrace();
                throw new CustomException(ResponseResultEnum.DELETE_ERROR);
            }
        }else{
            throw new CustomException(ResponseResultEnum.NO_USER);
        }
    }

    @Override
    public User getUserByName(String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        try{
            return userMapper.selectOne(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }
}
