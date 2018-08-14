package com.summer.litegithub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.NavigationContract;
import com.summer.litegithub.data.NaviBean;
import com.summer.litegithub.presenter.NavigationPresenter;
import com.summer.litegithub.ui.adapter.NavigationAdapter;
import com.summer.litegithub.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.fragment
 *  文件名:   NavigationFragment
 *  创建者:   Summers
 *  创建时间: 2018/8/1419:41
 *  描述：    TODO
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter>
        implements NavigationContract.View {

    @BindView(R.id.navi_recycle_view)
    RecyclerView mNaviRecycleView;
    Unbinder unbinder;
    private NavigationPresenter mPresenter;
    private NavigationAdapter mAdapter;
    private List<NaviBean> mNaviBeanList;

    @Override
    protected void initView() {
        mNaviRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        mPresenter = new NavigationPresenter(this);
        mNaviBeanList = new ArrayList<>();
        mAdapter = new NavigationAdapter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void getNavigationListSuccess(List<NaviBean> naviBeanList) {
        if (mAdapter == null) {
            return;
        }

    }

    @Override
    public void getNavigationListFail(String errorMessage) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
