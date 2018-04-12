package com.njust.utils;

import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.config.security.UserInfo;
import com.njust.dao.baseDao.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static Boolean IsNull(String str){
        if(str == null || str.trim().equals("")){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

   // 用户是否存在
    public Boolean isExist(Integer userId){
        if (userMapper.selectByPrimaryKey(userId) != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    //获取登录用户id
    public static ResponseResult getLoginId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserInfo) {
            return ResponseResultUtil.success(((UserInfo)principal).getUserid());
        }else {
            return ResponseResultUtil.error(ResponseResultEnum.NOT_LOGIN);
        }
    }
    //获取登录用户密码
    public static ResponseResult getLoginPwd(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserInfo) {
            return ResponseResultUtil.success(((UserInfo)principal).getPassword());
        }else {
            return ResponseResultUtil.error(ResponseResultEnum.NOT_LOGIN);
        }
    }
}
