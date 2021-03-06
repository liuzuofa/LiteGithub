package com.summer.litegithub.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.litegithub.base.app.MyApplication;
import com.summer.litegithub.base.presenter.AbsPresenter;
import com.summer.litegithub.base.view.AbstractView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.fragment
 *  文件名:   BaseFragment
 *  创建者:   Summers
 *  创建时间: 2018/7/1615:43
 *  描述：    TODO
 */
public abstract class BaseFragment <T extends AbsPresenter> extends SupportFragment implements AbstractView{
    public View mRootView;
    protected Activity mActivity;
    protected MyApplication mContext;
    protected final String TAG = "BaseFragment";
    @Inject
    T mPresenter;

    public BaseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        mActivity = getActivity();
        mContext = MyApplication.getInstance();
        initBind(view);
        initView();
        initData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    protected abstract void initView();

    protected abstract void initData();

    private void initBind(View view) {
        ButterKnife.bind(this,view);
    }

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

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
    public void showNormal() {

    }

    @Override
    public void reload() {

    }
}
