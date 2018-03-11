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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
        if (user.getUserName()==null || user.getUserName().trim().equals("") || user.getPrivLevel()==null || password==null || password.trim().equals("")){
            throw new CustomException(ResponseResultEnum.MISSING_DATA);
        }
        //判断用户名是否重复
        if(new UserUtil(userMapper).IsSameName(user.getUserName().trim())){
            throw new CustomException(ResponseResultEnum.SAME_NAME);
        }
        try{
            //插入用户信息
            userMapper.insertSelective(user);
            //获取插入用户的id
            Integer userId = userMapper.selectOne(user).getUserId();

            //插入用户密码
            //密码加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UserPwd userPwd = new UserPwd();
            userPwd.setPassword(encoder.encode(password.trim()));
            userPwd.setUserId(userId);
            userPwdMapper.insert(userPwd);

            //插入用户角色
            UserRole userRole = new UserRole();
            userRole.setUid(userId);
            userRole.setRid(user.getPrivLevel());
            return userRoleMapper.insert(userRole);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.INSERT_ERROR);
        }
    }

    @Override
    @Cacheable
    public PageInfo<User> getAll(String order, Integer page, Integer pageSize, User user) throws Exception{
        try{
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            example.and().andEqualTo("privLevel", user.getPrivLevel());
            if (user.getUserName() != null && user.getUserName().trim().equals("")){
                criteria.andLike("userName", "%"+user.getUserName()+"%");
            }
            if (user.getUserEmail() != null && user.getUserEmail().trim().equals("")){
                criteria.andLike("userEmail", "%"+user.getUserEmail()+"%");
            }
            PageHelper.startPage(page, pageSize, order);
            return new PageInfo<>(userMapper.selectByExample(example));
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user, String password) throws Exception {
        if (user.getUserName()==null || user.getUserName().trim().equals("") || user.getPrivLevel()==null){
            throw new CustomException(ResponseResultEnum.MISSING_DATA);
        }
        //如果用户名改变了，且和别的用户名字重复
        if(!userMapper.selectByPrimaryKey(user.getUserId()).getUserName().equals(user.getUserName().trim()) && new UserUtil(userMapper).IsSameName(user.getUserName().trim())){
            throw new CustomException(ResponseResultEnum.SAME_NAME);
        }
        try {
            userMapper.updateByPrimaryKeySelective(user);
            if(password!=null && !password.trim().equals("")){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                UserPwd userPwd = new UserPwd();
                userPwd.setUserId(user.getUserId());
                userPwd = userPwdMapper.selectOne(userPwd);
                userPwd.setPassword(encoder.encode(password.trim()));
                userPwdMapper.updateByPrimaryKey(userPwd);
            }else{
                throw new CustomException((ResponseResultEnum.MISSING_PWD));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(String userIds, Integer priv) throws Exception {
        try {
            String[] userIdArray = userIds.split("&");
            int count = 0;
            for (String userId : userIdArray) {
                if (userMapper.selectByPrimaryKey(Integer.parseInt(userId.trim())).getPrivLevel()==priv){
                    count += userMapper.deleteByPrimaryKey(Integer.parseInt(userId.trim()));
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.DELETE_ERROR);
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

    @Override
    public int updateName(Integer userId, String userName) throws Exception {
        if (userName.trim().equals("")){
            throw new CustomException(ResponseResultEnum.MISSING_NAME);
        }
        try{
            User user = new User();
            user = userMapper.selectByPrimaryKey(userId);
            user.setUserName(userName);
            return userMapper.updateByPrimaryKey(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.UPDATE_ERROR);
        }
    }
}
