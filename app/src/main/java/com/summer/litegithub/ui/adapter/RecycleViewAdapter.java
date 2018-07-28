package com.summer.litegithub.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.litegithub.R;
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
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<BannerBean> mBannerBeanList;
    public RecycleViewAdapter(List<BannerBean> bannerBeanList) {
        mBannerBeanList = bannerBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_recycle);
        }


    }
}
