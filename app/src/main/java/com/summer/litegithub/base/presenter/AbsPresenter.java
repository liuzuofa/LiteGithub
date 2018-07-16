package com.summer.litegithub.base.presenter;

import com.summer.litegithub.base.view.AbstractView;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.presenter
 *  文件名:   AbsPresenter
 *  创建者:   Summers
 *  创建时间: 2018/7/1614:00
 *  描述：    TODO
 */
public interface AbsPresenter<T extends AbstractView> {

    /**
     * 注入view
     * @param view
     */
    void attachView(T view);

    /**
     * 回收view
     * @param view
     */
    void detachView(T view);

}
