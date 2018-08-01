package com.summer.litegithub.ui.fragment;

import android.support.v4.app.Fragment;

import com.summer.litegithub.base.fragment.BaseFragment;
import com.summer.litegithub.contract.TreeContract;
import com.summer.litegithub.presenter.TreePresenter;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.fragment
 *  文件名:   TreeFragment
 *  创建者:   Summers
 *  创建时间: 2018/8/119:59
 *  描述：    TODO
 */
public class TreeFragment extends BaseFragment<TreePresenter> implements TreeContract.View{

    public static TreeFragment mTreeFragment = null;

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
        return 0;
    }
}
