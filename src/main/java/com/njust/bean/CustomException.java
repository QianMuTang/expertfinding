package com.njust.bean;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException{

    private Integer code;

    public CustomException(ResponseResultEnum responseResultEnum){
        super(responseResultEnum.getMsg());
        this.code = responseResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
