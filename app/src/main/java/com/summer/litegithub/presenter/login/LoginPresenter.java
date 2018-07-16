package com.summer.litegithub.presenter.login;

import com.summer.litegithub.Contract.LoginContract;
import com.summer.litegithub.base.presenter.BasePresenter;

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
    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void login(String name, String password) {

    }
}
