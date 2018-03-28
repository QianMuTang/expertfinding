package com.njust.bean;

/**
 * 自定义错误的响应种类，待修改......
 */
public enum ResponseResultEnum {
    NOT_LOGIN(4058000, "未登录"),
    LOGIN_FAILURE(4058001, "用户名或密码错误"),
    INSERT_ERROR(4058002, "插入失败"),
    DELETE_ERROR(4058003, "删除失败"),
    UPDATE_ERROR(4058004, "更新失败"),
    SEARCH_ERROR(4058005, "查询失败"),
    NO_PERMISSION(4058006, "无权限"),
    SAME_NAME(4058007, "重名"),
    MISSING_DATA(4058008,"数据缺失"),
    EMPTY_FILE(4058009, "文件不能为空"),
    UPLOAD_FAIL(40580010, "文件上传失败"),
    NOT_SAME_PWD(40580011, "密码不一致"),
    MISSING_PWD(40580012, "密码为空"),
    MISSING_NEW_PWD(40580013, "请输入新密码"),
    MISSING_NAME(40580014, "请输入新的用户名"),
    LOGIN_SUCCESS(40580015, "登录成功"),
    ERROR_DATEFORMAT(40580016, "日期格式不正确"),
    MAIL_ERROR(405800000, "邮件发送失败");

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
