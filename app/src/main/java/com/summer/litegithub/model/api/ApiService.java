package com.summer.litegithub.model.api;

import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;
import com.summer.litegithub.data.UserBean;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.model.api
 *  文件名:   ApiService
 *  创建者:   Summers
 *  创建时间: 2018/7/1715:28
 *  描述：    TODO
 */
public interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserBean>> login(@Field("username") String username, @Field("password") String password);

    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBanner();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleBean>> getArticleListByPage(@Path("page") int num);

}
