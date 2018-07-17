package com.summer.litegithub.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.model.api
 *  文件名:   LoginHttpIntercept
 *  创建者:   Summers
 *  创建时间: 2018/7/1710:13
 *  描述：    TODO
 */
class LoginHttpIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();

        return null;
    }
}
