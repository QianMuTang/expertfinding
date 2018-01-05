package com.njust.service.impl;

import com.njust.bean.baseBean.Role;
import com.njust.bean.baseBean.UserRole;
import com.njust.dao.RoleMapperExtend;
import com.njust.dao.UserRoleMapperExtend;
import com.njust.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRoleMapperExtend userRoleMapperExtend;

    @Autowired
    private RoleMapperExtend roleMapperExtend;

    @Override
    public String findRole(int uid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        Integer rid = userRoleMapperExtend.selectOne(userRole).getRid();

        Role role = new Role();
        role.setId(rid);
        return roleMapperExtend.selectOne(role).getRoleName();
    }
}
