package com.summer.litegithub.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.summer.litegithub.base.App.MyApplication;
import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.activity
 *  文件名:   BaseActivity
 *  创建者:   Summers
 *  创建时间: 2018/7/1613:58
 *  描述：    TODO
 */
public abstract class BaseActivity<T extends AbsPresenter> extends SupportActivity implements AbstractView {

    protected MyApplication mContext;
    protected BaseActivity mBaseActivity;
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = MyApplication.getInstance();
        mBaseActivity = this;
        initBind();
        initInject();
        onViewCreated();
        initToolbar();
        initView();
        initData();
    }

    private void initToolbar() {

    }

    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    public void initBind() {
        ButterKnife.bind(this);
    }

    private void initInject() {

    }

    /**
     * 获取布局ID
     *
     * @return viewId
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void reload() {

    }
}
