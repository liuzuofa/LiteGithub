package com.summer.litegithub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.HomeContract;
import com.summer.litegithub.data.BannerBean;
import com.summer.litegithub.presenter.HomePresenter;
import com.summer.litegithub.ui.adapter.RecycleViewAdapter;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.fragment
 *  文件名:   HomeFragment
 *  创建者:   Summers
 *  创建时间: 2018/7/1920:08
 *  描述：    TODO
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private static HomeFragment mHomeFragment;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private LinearLayout mBannerView;
    private HomePresenter mHomePresenter;
    private RecycleViewAdapter mAdapter;

    public HomeFragment() {
        Log.d(TAG, "HomeFragment Construct");
    }

    public static HomeFragment getInstance() {
        if (mHomeFragment == null) {
            synchronized (HomeFragment.class) {
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
            }
        }
        return mHomeFragment;
    }

    @Override
    protected void initData() {
        mHomePresenter = new HomePresenter(this);

    }

    @Override
    protected void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner,null);
        //mBanner = mBannerView.findViewById(R.id.banner);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void autoLoginSuccess() {

    }

    @Override
    public void autoLoginFail() {

    }

    @Override
    public void getBannerSuccess(List<BannerBean> bannerList) {

    }

    @Override
    public void getBannerFail(String info) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }
}
