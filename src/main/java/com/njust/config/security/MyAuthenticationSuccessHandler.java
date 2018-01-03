package com.njust.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.utils.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理逻辑
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException, CustomException {
        logger.info("登录成功");
        //1、什么都不做的话，那就直接调用父类的方法
        super.onAuthenticationSuccess(request, response, authentication);

        //2、如果是返回json格式，那么我们这么写
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(objectMapper.writeValueAsString(ResponseResultUtil.error(ResponseResultEnum.LOGIN_SUCCESS)));

        //3、如果是要跳转到某个页面的，比如我们的那个whoim的则
//        new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");

    }
}
