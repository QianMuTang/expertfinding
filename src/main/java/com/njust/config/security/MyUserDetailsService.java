package com.njust.config.security;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.User;
import com.njust.service.UserService;
import com.sun.org.apache.regexp.internal.REUtil;
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
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        User user = new User();
//        try {
        try {
            user = userService.getUserByName(username);
            if (user != null) {
                return new UserInfo(user.getUserName(), user.getPassword(), "ROLE_ADMIN", true, true, true, true);
            }else{
                throw new UsernameNotFoundException("用户名 " + username + " 找不到");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}