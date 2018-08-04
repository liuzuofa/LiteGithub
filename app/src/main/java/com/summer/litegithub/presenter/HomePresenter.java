package com.summer.litegithub.presenter;

import android.util.Log;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.HomeContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;
import com.summer.litegithub.model.api.ApiContent;
import com.summer.litegithub.model.api.ApiService;
import com.summer.litegithub.model.api.BaseResponse;
import com.summer.litegithub.model.api.HttpObserver;
import com.summer.litegithub.model.constant.Constant;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.presenter
 *  文件名:   HomePresenter
 *  创建者:   Summers
 *  创建时间: 2018/7/1920:11
 *  描述：    TODO
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private HomeContract.View mView;
    private String TAG = "HomePresenter";

    public HomePresenter(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void autoLogin() {

    }

    @Override
    public void getBanner() {
        ApiContent.createApi(ApiService.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<List<BannerBean>>>() {
                    @Override
                    public void onNext(BaseResponse<List<BannerBean>> listBaseResponse) {
                        if (listBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getBannerSuccess(listBaseResponse.getData());
                        } else if (listBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getBannerFail(listBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getBannerFail(e.getMessage());
                    }
                });
    }

    @Override
    public void getArticleListByPage(int page) {
        ApiContent.createApi(ApiService.class)
                .getArticleListByPage(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<ArticleBean>>() {
                    @Override
                    public void onNext(BaseResponse<ArticleBean> articleBeanBaseResponse) {
                        if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getArticleListByPageSuccess(articleBeanBaseResponse.getData());
                            Log.e(TAG, "onNext: " + articleBeanBaseResponse.getData().toString());
                        } else if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getArticleListByPageFail(articleBeanBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getArticleListByPageFail(e.getMessage());
                    }
                });
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }
}
