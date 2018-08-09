package com.summer.litegithub.contract;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;
import com.summer.litegithub.data.BannerBean;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.Contract
 *  文件名:   HomeContract
 *  创建者:   Summers
 *  创建时间: 2018/7/1916:36
 *  描述：    TODO
 */
public class TreeContract {

    public interface View extends AbstractView {
        void getTreeNavigationSuccess();
        void getTreeNavigationFail();
        void getTreeArticleListSuccess();
        void getTreeArticleListFail();
    }

    public interface Presenter extends AbsPresenter<View> {
        void getTreeNavigation();
        void getTreeArticleList(int subId);
    }
}
