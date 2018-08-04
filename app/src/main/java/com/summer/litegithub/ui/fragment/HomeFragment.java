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
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;
import com.summer.litegithub.presenter.HomePresenter;
import com.summer.litegithub.ui.adapter.RecycleViewAdapter;
import com.summer.litegithub.util.glide.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    private List<ArticleBean.Data> mArticleBeanDataList;
    private List<String> mBannerTitleList;
    private List<String> mBannerImageList;
    private List<String> mBannerLinkList;
    private Banner mBanner;

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
    protected void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner, null);
        mBanner = mBannerView.findViewById(R.id.banner);
        mBannerView.removeView(mBanner);
        mBannerView.addView(mBanner);

    }

    @Override
    protected void initData() {
        mArticleBeanDataList = new ArrayList<>();
        mBannerTitleList = new ArrayList<>();
        mBannerImageList = new ArrayList<>();
        mBannerLinkList = new ArrayList<>();
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getBanner();
        mHomePresenter.getArticleListByPage(1);
        mAdapter = new RecycleViewAdapter(R.layout.item_home_list, mArticleBeanDataList);
        mAdapter.addHeaderView(mBannerView);
        mRecycleView.setAdapter(mAdapter);
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
        mBannerTitleList.clear();
        mBannerImageList.clear();
        mBannerLinkList.clear();
        for (BannerBean bannerBean : bannerList) {
            mBannerTitleList.add(bannerBean.getTitle());
            mBannerImageList.add(bannerBean.getImagePath());
            mBannerLinkList.add(bannerBean.getUrl());
        }
        mBanner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImages(mBannerImageList)
                .setBannerTitles(mBannerTitleList)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();

    }

    @Override
    public void getBannerFail(String errorMessage) {
        Log.e(TAG, "getBannerFail: " + errorMessage);
    }

    @Override
    public void getArticleListByPageSuccess(ArticleBean articleBean) {
        if (mAdapter == null) {
            return;
        }
        mArticleBeanDataList = articleBean.getDataList();
        if (mArticleBeanDataList != null) {
            mAdapter.replaceData(mArticleBeanDataList);
        }
    }

    @Override
    public void getArticleListByPageFail(String errorMessage) {
        Log.e(TAG, "getArticleListByPageFail: " + errorMessage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }
}
