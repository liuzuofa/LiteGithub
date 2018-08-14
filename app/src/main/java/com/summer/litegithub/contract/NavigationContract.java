package com.summer.litegithub.contract;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;
import com.summer.litegithub.data.NaviBean;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.contract
 *  文件名:   NavigationContract
 *  创建者:   Summers
 *  创建时间: 2018/8/1417:55
 *  描述：    TODO
 */
public class NavigationContract {
    public interface View extends AbstractView{
        void getNavigationListSuccess(List<NaviBean> naviBeanList);
        void getNavigationListFail(String errorMessage);
    }

    public interface Presenter extends AbsPresenter<View> {
        void getNavigationList();
    }
}
