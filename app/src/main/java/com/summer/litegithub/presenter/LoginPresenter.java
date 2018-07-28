package com.summer.litegithub.presenter;

import android.util.Log;

import com.summer.litegithub.contract.LoginContract;
import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.data.User;
import com.summer.litegithub.model.api.ApiContent;
import com.summer.litegithub.model.api.ApiService;
import com.summer.litegithub.model.api.BaseResponse;
import com.summer.litegithub.model.api.HttpObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.presenter.login
 *  文件名:   LoginPresenter
 *  创建者:   Summers
 *  创建时间: 2018/7/1619:34
 *  描述：    TODO
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private LoginContract.View mView;
    private String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void login(String name, String password) {
        ApiContent.createApi(ApiService.class)
                .login(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<User>>() {
                    @Override
                    public void onNext(BaseResponse<User> userBaseResponse) {
                        Log.d(TAG, "onNext: code :" + userBaseResponse.getErrorCode() );
                        if (userBaseResponse.getErrorCode() == 0 ) {
                            mView.loginSuccess(userBaseResponse.getData());
                        } else if (userBaseResponse.getErrorCode() == -1) {
                            mView.loginFail(userBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        if (e.getMessage() != null) {
                            mView.loginFail(e.getMessage());
                        }
                    }
                });
    }
}
