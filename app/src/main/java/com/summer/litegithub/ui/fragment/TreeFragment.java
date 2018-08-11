package com.summer.litegithub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.TreeContract;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.TreeNaviBean;
import com.summer.litegithub.presenter.TreePresenter;
import com.summer.litegithub.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.fragment
 *  文件名:   TreeFragment
 *  创建者:   Summers
 *  创建时间: 2018/8/119:59
 *  描述：    TODO
 */
public class TreeFragment extends BaseFragment<TreePresenter> implements TreeContract.View, TabLayout.OnTabSelectedListener {

    public static TreeFragment mTreeFragment = null;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    Unbinder unbinder;
    @BindView(R.id.sub_tab_layout)
    TabLayout mSubTabLayout;
    @BindView(R.id.tree_recycle_view)
    RecyclerView mTreeRecycleView;
    private TreePresenter mTreePresenter;
    private List<TreeNaviBean> mTreeNaviBeans;
    private List<String> mSubTitle;
    private RecyclerViewAdapter mAdapter;
    private List<ArticleBean.Datas> mArticleBeanDataList;
    private int mTotalPage;
    private int mCurrentPage;

    public static Fragment getInstance() {
        synchronized (TreeFragment.class) {
            if (mTreeFragment == null) {
                synchronized (TreeFragment.class) {
                    if (mTreeFragment == null) {
                        mTreeFragment = new TreeFragment();
                    }
                }
            }
        }
        return mTreeFragment;
    }

    @Override
    protected void initView() {
        mTreeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.addOnTabSelectedListener(this);
        mSubTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSubTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        mSubTitle = new ArrayList<>();
        mArticleBeanDataList = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(R.layout.item_home_list, mArticleBeanDataList);
        mTreePresenter = new TreePresenter(this);
        mTreePresenter.getTreeNavigation();
        mTreeRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree;
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
    public void getTreeNavigationSuccess(List<TreeNaviBean> naviBeans) {
        if (naviBeans != null) {
            mTreeNaviBeans = naviBeans;
            for (TreeNaviBean treeNaviBean : naviBeans) {
                mTabLayout.addTab(mTabLayout.newTab()
                        .setText(treeNaviBean.getName())
                        .setTag(mTabLayout));
            }
        }
    }

    @Override
    public void getTreeNavigationFail(String errorMessage) {
        Log.e(TAG, "getTreeNavigationFail: " + errorMessage);
    }

    @Override
    public void getTreeArticleListSuccess(ArticleBean articleBean) {
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
    public void getTreeArticleListFail(String errorMessage) {

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() == mTabLayout) {
            Log.d(TAG, "onTabSelected: " + tab.getText());
            addSubTab(tab.getPosition());
        }
        if (tab.getTag() == mSubTabLayout) {
            Log.d(TAG, "onSubTabSelected: " + tab.getText());
            mTreePresenter.getTreeArticleList(0, Integer.parseInt((String) tab.getContentDescription()));
        }
    }

    private void addSubTab(int position) {
        for (TreeNaviBean.Children children : mTreeNaviBeans.get(position).getChildren()) {
            mSubTabLayout.addTab(mSubTabLayout.newTab()
                    .setText(children.getName())
                    .setTag(mSubTabLayout)
                    .setContentDescription(String.valueOf(children.getId())));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getTag() == mTabLayout) {
            mSubTabLayout.removeAllTabs();
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.d(TAG, "onTabReselected: " + tab.getText());
    }
}
