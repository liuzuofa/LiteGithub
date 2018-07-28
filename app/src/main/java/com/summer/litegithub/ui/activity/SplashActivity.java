package com.summer.litegithub.ui.activity;

import com.summer.litegithub.R;
import com.summer.litegithub.base.activity.BaseActivity;
import com.summer.litegithub.util.app.JumpUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.main
 *  文件名:   SplashActivity
 *  创建者:   Summers
 *  创建时间: 2018/7/1616:07
 *  描述：    TODO
 */
public class SplashActivity extends BaseActivity {

    private Disposable mTimer;
    private int mTime = 3;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mTimer = Observable.interval(0,1, TimeUnit.SECONDS)
                .take(mTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mTime - aLong == 1 && !mTimer.isDisposed()) {    //aLong -> 0,1,2
                        gotoActivity();
                    }
                });
    }

    private void gotoActivity() {
        JumpUtil.startActivity(mContext, LoginActivity.class);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer.isDisposed() && mTimer != null) {
            mTimer.dispose();
            mTimer = null;
        }
    }
}
