package com.njust.config.security;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.service.UserPwdService;
import com.njust.service.UserRoleService;
import com.njust.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPwdService userPwdService;

    @Override
    public UserDetails loadUserByUsername(String username) throws CustomException, UsernameNotFoundException {

        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        try {
            User user = userService.getUserByName(username);
            if (user != null) {
                String password = userPwdService.getPwdById(user.getUserId());
                String roleName = userRoleService.findRole(user.getUserId());
                return new UserInfo(user.getUserId(), user.getUserName(), password, roleName, true, true, true, true);
            }else{
                throw new UsernameNotFoundException("UserName " + username + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof UsernameNotFoundException){
                throw (UsernameNotFoundException)e;
            }
            throw new CustomException(ResponseResultEnum.SEARCH_ERROR);
        }
    }
}