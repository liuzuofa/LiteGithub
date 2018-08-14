package com.summer.litegithub.contract;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.ProjectNaviBean;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.contract
 *  文件名:   ProjectContract
 *  创建者:   Summers
 *  创建时间: 2018/8/1415:34
 *  描述：    TODO
 */
public class ProjectContract {
    public interface View extends AbstractView {
        void getProjectNaviListSuccess(List<ProjectNaviBean> projectNaviBeans);
        void getProjectNaviListFail(String errorMessage);
        void getProjectArticleListSuccess(ArticleBean articleBean);
        void getProjectArticleListFail(String errorMessage);
    }

    public interface Presenter extends AbsPresenter<View> {
        void getProjectNaviList();
        void getProjectArticleList(int page, int cid);
    }
}
