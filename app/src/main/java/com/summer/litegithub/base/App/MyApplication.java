package com.summer.litegithub.base.App;

import android.app.Application;

import com.summer.litegithub.model.constant.Constant;
import com.tencent.bugly.crashreport.CrashReport;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.base.App
 *  文件名:   MyApplication
 *  创建者:   Summers
 *  创建时间: 2018/7/1614:28
 *  描述：    TODO
 */
public class MyApplication extends Application {

    private static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        CrashReport.initCrashReport(this, Constant.BUGLY_ID,false);
    }

    public static synchronized MyApplication getInstance() {
        return mMyApplication;
    }
}
