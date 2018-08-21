package com.summer.litegithub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.HomeContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;
import com.summer.litegithub.model.constant.Constant;
import com.summer.litegithub.presenter.HomePresenter;
import com.summer.litegithub.ui.adapter.RecyclerViewAdapter;
import com.summer.litegithub.util.glide.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
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
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View,
        BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener{

    private static HomeFragment mHomeFragment;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private LinearLayout mBannerView;
    private HomePresenter mHomePresenter;
    private RecyclerViewAdapter mAdapter;
    private List<ArticleBean.Datas> mArticleBeanDataList;
    private List<String> mBannerTitleList;
    private List<String> mBannerImageList;
    private List<String> mBannerLinkList;
    private Banner mBanner;
    private int mCurrentPage = 0;
    private int mTotalPage;

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
        setRefresh();
        mArticleBeanDataList = new ArrayList<>();
        mBannerTitleList = new ArrayList<>();
        mBannerImageList = new ArrayList<>();
        mBannerLinkList = new ArrayList<>();
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getBanner();
        mHomePresenter.getArticleListByPage(mCurrentPage);
        mAdapter = new RecyclerViewAdapter(R.layout.item_home_list, mArticleBeanDataList);
        mAdapter.addHeaderView(mBannerView);
        mRecycleView.setAdapter(mAdapter);
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mCurrentPage = 0;
                mHomePresenter.getArticleListByPage(mCurrentPage);
                mRefreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mCurrentPage < mTotalPage - 1) {
                    mCurrentPage++;
                    Log.e(TAG, "onLoadMoreRequested: " + mCurrentPage);
                    mHomePresenter.loadMore(mCurrentPage);
                    mRefreshLayout.finishLoadMore(1000);
                }
            }
        });
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
        mTotalPage = articleBean.getPageCount();
        mArticleBeanDataList = articleBean.getDatas();
        if (mArticleBeanDataList != null) {
            if (mCurrentPage == 0) {
                mAdapter.replaceData(mArticleBeanDataList);
            } else {
                mAdapter.addData(mArticleBeanDataList);
            }
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
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARTICLE_TITLE,mAdapter.getData().get(position).getTitle());
        bundle.putString(Constant.ARTICLE_LINK,mAdapter.getData().get(position).getLink());
        bundle.putBoolean(Constant.ARTICLE_IS_COLLECTED,mAdapter.getData().get(position).isCollect());
        startActivity(new Intent(mActivity,WebViewFragment.class).putExtras(bundle));

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
