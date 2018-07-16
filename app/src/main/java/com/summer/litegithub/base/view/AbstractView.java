package com.summer.litegithub.base.view;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.view
 *  文件名:   AbstractView
 *  创建者:   Summers
 *  创建时间: 2018/7/1613:58
 *  描述：    TODO
 */
public interface AbstractView {

    void showNormal();

    void showError();

    void showLoading();

    void showEmpty();

    void reload();
}
