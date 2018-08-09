package com.summer.litegithub.presenter;

import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.TreeContract;
import com.summer.litegithub.data.TreeNaviBean;
import com.summer.litegithub.model.api.ApiContent;
import com.summer.litegithub.model.api.ApiService;
import com.summer.litegithub.model.api.BaseResponse;
import com.summer.litegithub.model.api.HttpObserver;

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

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getTreeArticleList(int subId) {

    }
}
