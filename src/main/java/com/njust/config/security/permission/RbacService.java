package com.njust.config.security.permission;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断当前的用户所拥有的URL是否和当前访问的URL是否匹配
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
