package com.summer.litegithub.util.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.summer.litegithub.base.app.MyApplication;
import com.youth.banner.loader.ImageLoader;


/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.util.glide
 *  文件名:   GlideImageLoader
 *  创建者:   Summers
 *  创建时间: 2018/7/3120:58
 *  描述：    TODO
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(path).into(imageView);
    }
}
