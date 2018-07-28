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
public class HomeContract {

    public interface View extends AbstractView {
        void autoLoginSuccess();
        void autoLoginFail();
        void getBannerSuccess(List<BannerBean> bannerList);
        void getBannerFail(String info);
    }

    public interface Presenter extends AbsPresenter<View> {
        void autoLogin();
        void getBanner();
        void getHomeItemInfo(int page);
        void loadMore();
        void refresh();
    }
}
