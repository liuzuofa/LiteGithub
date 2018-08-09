package com.summer.litegithub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.summer.litegithub.R;
import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.TreeContract;
import com.summer.litegithub.presenter.TreePresenter;

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
public class TreeFragment extends BaseFragment<TreePresenter> implements TreeContract.View {

    public static TreeFragment mTreeFragment = null;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.tree_frame_layout)
    FrameLayout mTreeFrameLayout;
    Unbinder unbinder;

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

    }

    @Override
    protected void initData() {

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
    public void getTreeNavigationSuccess() {

    }

    @Override
    public void getTreeNavigationFail() {

    }

    @Override
    public void getTreeArticleListSuccess() {

    }

    @Override
    public void getTreeArticleListFail() {

    }
}
