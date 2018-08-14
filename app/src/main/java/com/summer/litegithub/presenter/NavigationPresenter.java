package com.summer.litegithub.presenter;

import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.NavigationContract;
import com.summer.litegithub.data.NaviBean;
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
 *  文件名:   NavigationPresenter
 *  创建者:   Summers
 *  创建时间: 2018/8/1419:35
 *  描述：    TODO
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter {

    private NavigationContract.View mView;

    public NavigationPresenter(NavigationContract.View view) {
        mView = view;
    }

    @Override
    public void getNavigationList() {
        ApiContent.createApi(ApiService.class)
                .getNavigationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<List<NaviBean>>>() {
                    @Override
                    public void onNext(BaseResponse<List<NaviBean>> listBaseResponse) {
                        if (listBaseResponse.getErrorCode() == Constant.REQUEST_SUCCESS) {
                            mView.getNavigationListSuccess(listBaseResponse.getData());
                        } else if (listBaseResponse.getErrorCode() == Constant.REQUEST_ERROR) {
                            mView.getNavigationListFail(listBaseResponse.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getNavigationListFail(e.getMessage());
                    }
                });
    }
}
