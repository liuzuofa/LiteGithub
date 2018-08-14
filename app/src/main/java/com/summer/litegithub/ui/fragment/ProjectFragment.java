package com.summer.litegithub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.ProjectContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.ProjectNaviBean;
import com.summer.litegithub.presenter.ProjectPresenter;
import com.summer.litegithub.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.fragment
 *  文件名:   ProjectFragment
 *  创建者:   Summers
 *  创建时间: 2018/8/1416:01
 *  描述：    TODO
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter>
        implements ProjectContract.View, TabLayout.OnTabSelectedListener {

    private static ProjectFragment mProjectFragment;
    @BindView(R.id.project_tab_layout)
    TabLayout mProjectTabLayout;
    @BindView(R.id.project_recycle_view)
    RecyclerView mProjectRecycleView;
    Unbinder unbinder;
    private ProjectPresenter mPresenter;
    private RecyclerViewAdapter mAdapter;
    private List<ArticleBean.Datas> mArticleBeanDataList;
    private int mTotalPage;
    private int mCurrentPage;

    public static ProjectFragment getInstance() {
        synchronized (ProjectFragment.class) {
            if (mProjectFragment == null) {
                synchronized (ProjectFragment.class) {
                    if (mProjectFragment == null) {
                        mProjectFragment = new ProjectFragment();
                    }
                }
            }
        }
        return mProjectFragment;
    }

    @Override
    protected void initView() {
        mProjectRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProjectTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mProjectTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        mArticleBeanDataList = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(R.layout.item_home_list,mArticleBeanDataList);
        mPresenter = new ProjectPresenter(this);
        mPresenter.getProjectNaviList();
        mProjectRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void getProjectNaviListSuccess(List<ProjectNaviBean> projectNaviBeans) {
        for (ProjectNaviBean bean : projectNaviBeans) {
            mProjectTabLayout.addTab(mProjectTabLayout.newTab().setText(bean.getName())
                    .setContentDescription(String.valueOf(bean.getId())));
        }
    }

    @Override
    public void getProjectNaviListFail(String errorMessage) {

    }

    @Override
    public void getProjectArticleListSuccess(ArticleBean articleBean) {
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
    public void getProjectArticleListFail(String errorMessage) {

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
    public void onTabSelected(TabLayout.Tab tab) {
        mPresenter.getProjectArticleList(0,Integer.parseInt((String) tab.getContentDescription()));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
