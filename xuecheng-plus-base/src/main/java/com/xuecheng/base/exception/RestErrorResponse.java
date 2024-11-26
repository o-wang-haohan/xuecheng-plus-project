package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @Author: whh
 * @Description: TODO
 * @Date: 2024/11/5 下午2:16
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}