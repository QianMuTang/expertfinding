package com.njust.service.impl;

import com.njust.bean.baseBean.Role;
import com.njust.bean.baseBean.UserRole;
import com.njust.dao.baseDao.RoleMapper;
import com.njust.dao.baseDao.UserRoleMapper;
import com.njust.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String findRole(int uid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        Integer rid = userRoleMapper.selectOne(userRole).getRid();

        Role role = new Role();
        role.setId(rid);
        return roleMapper.selectOne(role).getRoleName();
    }
}
