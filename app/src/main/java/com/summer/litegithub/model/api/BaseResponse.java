package com.summer.litegithub.model.api;

/*
 *  项目名：  LiteGithub
 *  包名：    com.summer.litegithub.model.api
 *  文件名:   BaseResponse
 *  创建者:   Summers
 *  创建时间: 2018/7/17 23:00
 *  描述：    TODO
 */
public class BaseResponse<T> {
    public T data;
    public int errorCode;
    public String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
