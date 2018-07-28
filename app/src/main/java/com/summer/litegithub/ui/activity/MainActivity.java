package com.summer.litegithub.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.summer.litegithub.R;
import com.summer.litegithub.base.activity.BaseActivity;
import com.summer.litegithub.base.presenter.BasePresenter;
import com.summer.litegithub.contract.HomeContract;
import com.summer.litegithub.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomeContract.Presenter> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_container)
    FrameLayout mContainer;
    @BindView(R.id.bottom_nav_view)
    BottomNavigationView mBottomNavView;
    private BasePresenter mPresenter;
    private List<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;
    private int mCurrentIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPresenter = new BasePresenter();
        initFragment();
        setCurrentFragment(0);
    }

    private void setCurrentFragment(int position) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        if (!mFragmentList.get(position).isAdded()) {
            mFragmentManager.beginTransaction().add(R.id.fl_container,
                    mFragmentList.get(position),null).commit();
            mCurrentIndex = position;
        }
        mFragmentManager.beginTransaction().hide(mFragmentList.get(mCurrentIndex))
                .show(mFragmentList.get(position)).commit();
        mCurrentIndex = position;
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(HomeFragment.getInstance());
    }

    @Override
    protected void initData() {

    }
}
