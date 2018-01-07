package com.njust.utils;

import com.njust.bean.ResponseResult;
import com.njust.bean.ResponseResultEnum;

/**
 * 装载向前台响应的数据，成功默认code:1,msg:success，错误为ResponseResultEnum类中定义的错误类别
 */
public class ResponseResultUtil {

    public static ResponseResult success(Object object){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(1);
        responseResult.setMsg("success");
        responseResult.setData(object);
        return responseResult;
    }

    public static ResponseResult success(){
        return success(null);
    }

    public static ResponseResult error(Integer code, String msg){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        return responseResult;
    }

    public static ResponseResult error(ResponseResultEnum responseResultEnum){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(responseResultEnum.getCode());
        responseResult.setMsg(responseResultEnum.getMsg());
        return responseResult;
    }
}
