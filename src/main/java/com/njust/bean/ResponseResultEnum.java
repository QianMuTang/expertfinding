package com.njust.bean;

/**
 * 自定义错误的响应种类，待修改......
 */
public enum ResponseResultEnum {
    NO_USER(4058000, "用户不存在"),
    LOGIN_FAILURE(4058001, "登录失败"),
    PASSWORD_ERROR(4058, "密码错误"),
    NOT_MANAGER(4058002, "不是管理员"),
    INSERT_ERROR(4058003, "插入错误"),
    SEARCH_ERROR(4058004, "查询错误"),
    MAIL_ERROR(4058005, "邮件发送失败");

    private Integer code;

    private String msg;

    ResponseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
