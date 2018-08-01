package com.summer.litegithub.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.litegithub.R;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.adapter
 *  文件名:   RecycleViewAdapter
 *  创建者:   Summers
 *  创建时间: 2018/7/2519:44
 *  描述：    TODO
 */
public class RecycleViewAdapter extends BaseQuickAdapter<List<ArticleBean>,BaseViewHolder> {

    public RecycleViewAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, List<ArticleBean> item) {

    }
}
