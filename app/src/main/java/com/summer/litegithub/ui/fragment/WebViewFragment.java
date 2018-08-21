package com.summer.litegithub.ui.fragment;

import android.os.Bundle;

import com.summer.litegithub.R;
import com.summer.litegithub.base.activity.BaseActivity;
import com.summer.litegithub.base.fragment.BaseFragment;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.activity
 *  文件名:   WebViewFragment
 *  创建者:   Summers
 *  创建时间: 2018/8/2120:25
 *  描述：    TODO
 */
public class WebViewFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_web_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle bundle = mActivity.getIntent().getExtras();

    }
}
