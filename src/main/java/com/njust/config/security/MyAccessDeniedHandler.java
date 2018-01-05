package com.njust.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njust.bean.ResponseResultEnum;
import com.njust.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拒绝访问处理逻辑
 */
@Component("myAccessDeniedHandler")
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {
        boolean isAjax = isAjaxRequest(request);
        logger.info("是否是ajax请求：{}", isAjax);
        /**
         * Ajax待定............
         */
        if(isAjax){
            request.setAttribute("isAjaxRequest", isAjax);
            request.setAttribute("message", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/authNotPass.jsp");
//            response.sendRedirect(request.getContextPath()+"/authNotPass.jsp");
            dispatcher.forward(request, response);
        }else{
            logger.info("权限不足");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseResultUtil.error(ResponseResultEnum.NO_PERMISSION)));
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
}
