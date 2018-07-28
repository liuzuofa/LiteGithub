package com.summer.litegithub.contract;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;
import com.summer.litegithub.data.User;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.Contract
 *  文件名:   LoginContract
 *  创建者:   Summers
 *  创建时间: 2018/7/1619:38
 *  描述：    TODO
 */
public class LoginContract {
    public interface View extends AbstractView {
        void loginSuccess(User user);
        void loginFail(String errorInfo);
    }

    public interface Presenter extends AbsPresenter<LoginContract.View> {
        void login(String name, String password);
    }
}
