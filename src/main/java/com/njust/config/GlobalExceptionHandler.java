package com.njust.config;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResult;
import com.njust.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常，如果是自定义异常，则报自定义异常，不是则报系统异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e){
        if (e instanceof CustomException){
            logger.error("=========="+e.getMessage()+"=========");
            CustomException customException = (CustomException) e;
            return ResponseResultUtil.error(customException.getCode(), customException.getMessage());
        }else{
            logger.error("=========【系统异常】{}=========", e);
            return ResponseResultUtil.error(-1, "系统异常，已经杀了一个程序员祭天！");
        }
    }
}
