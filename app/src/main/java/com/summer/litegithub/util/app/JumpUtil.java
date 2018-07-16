package com.summer.litegithub.util.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.util.app
 *  文件名:   JumpUtil
 *  创建者:   Summers
 *  创建时间: 2018/7/1617:36
 *  描述：    TODO
 */
public class JumpUtil {

    public static void startActivity(Context context, Class<? extends Activity> targetActivity) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
