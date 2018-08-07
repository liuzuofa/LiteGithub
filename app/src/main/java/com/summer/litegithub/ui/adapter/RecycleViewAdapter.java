package com.summer.litegithub.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.litegithub.R;
import com.summer.litegithub.data.ArticleBean;
import com.summer.litegithub.data.BannerBean;

import java.util.Date;
import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.adapter
 *  文件名:   RecycleViewAdapter
 *  创建者:   Summers
 *  创建时间: 2018/7/2519:44
 *  描述：    TODO
 */
public class RecycleViewAdapter extends BaseQuickAdapter<ArticleBean.Datas, BaseViewHolder> {

    public RecycleViewAdapter(int layoutResId, @Nullable List<ArticleBean.Datas> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.Datas item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_article_title, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_article_author, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getSuperChapterName()) &&
                !TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.tv_article_class, item.getChapterName());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_time, item.getNiceDate());
        }

    }
}
