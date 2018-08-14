package com.summer.litegithub.presenter;

import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.ProjectContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.ProjectNaviBean;
import com.summer.litegithub.model.api.ApiContent;
import com.summer.litegithub.model.api.ApiService;
import com.summer.litegithub.model.api.BaseResponse;
import com.summer.litegithub.model.api.HttpObserver;
import com.summer.litegithub.model.constant.Constant;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.presenter
 *  文件名:   ProjectPresenter
 *  创建者:   Summers
 *  创建时间: 2018/8/1415:52
 *  描述：    TODO
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {
    private ProjectContract.View mView;

    public ProjectPresenter(ProjectContract.View view) {
        mView = view;
    }

    @Override
    public void getProjectNaviList() {
        ApiContent.createApi(ApiService.class)
                .getProjectNavi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<List<ProjectNaviBean>>>() {
                    @Override
                    public void onNext(BaseResponse<List<ProjectNaviBean>> listBaseResponse) {
                        if (listBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getProjectNaviListSuccess(listBaseResponse.getData());
                        } else if (listBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getProjectNaviListFail(listBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getProjectArticleListFail(e.getMessage());
                    }
                });
    }

    @Override
    public void getProjectArticleList(int page, int cid) {
        ApiContent.createApi(ApiService.class)
                .getProjectListByPageAndCid(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<ArticleBean>>() {
                    @Override
                    public void onNext(BaseResponse<ArticleBean> articleBeanBaseResponse) {
                        if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getProjectArticleListSuccess(articleBeanBaseResponse.getData());
                        } else if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getProjectArticleListFail(articleBeanBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getProjectArticleListFail(e.getMessage());
                    }
                });
    }
}
