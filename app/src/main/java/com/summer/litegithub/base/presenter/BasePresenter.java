package com.summer.litegithub.base.presenter;

import com.summer.litegithub.base.view.AbstractView;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.presenter
 *  文件名:   BasePresenter
 *  创建者:   Summers
 *  创建时间: 2018/7/1615:58
 *  描述：    TODO
 */
public class BasePresenter<T extends AbstractView> implements AbsPresenter<T> {

    protected T mView;
    protected int mCurrentPage;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView(T view) {
        this.mView = null;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
    }
}
