package com.njust.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njust.bean.ResponseResultEnum;
import com.njust.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理逻辑
 */
@Component("myAuthenticationFailHandler")
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyAuthenticationFailHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException{

        logger.info("用户名或密码错误");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseResultUtil.error(ResponseResultEnum.LOGIN_FAILURE)));
    }
}
