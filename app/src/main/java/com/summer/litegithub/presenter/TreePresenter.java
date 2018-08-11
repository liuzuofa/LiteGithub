package com.summer.litegithub.presenter;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.TreeContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.TreeNaviBean;
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
 *  文件名:   TreePresenter
 *  创建者:   Summers
 *  创建时间: 2018/8/120:03
 *  描述：    TODO
 */
public class TreePresenter extends BasePresenter<TreeContract.View> implements TreeContract.Presenter {

    private TreeContract.View mView;
    private final static String TAG = "TreePresenter";

    public TreePresenter(TreeContract.View view) {
        this.mView = view;
    }

    @Override
    public void getTreeNavigation() {
        ApiContent.createApi(ApiService.class)
                .getTreeNavi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<List<TreeNaviBean>>>() {
                    @Override
                    public void onNext(BaseResponse<List<TreeNaviBean>> listBaseResponse) {
                        if (listBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getTreeNavigationSuccess(listBaseResponse.getData());
                        } else if (listBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getTreeNavigationFail(listBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getTreeNavigationFail(e.getMessage());
                    }
                });
    }

    @Override
    public void getTreeArticleList(int page, int cid) {
        ApiContent.createApi(ApiService.class)
                .getArticleListByPageAndCid(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<ArticleBean>>() {
                    @Override
                    public void onNext(BaseResponse<ArticleBean> articleBeanBaseResponse) {
                        if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getTreeArticleListSuccess(articleBeanBaseResponse.getData());
                        } else if (articleBeanBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getTreeArticleListFail(articleBeanBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getTreeArticleListFail(e.getMessage());
                    }
                });
    }
}
