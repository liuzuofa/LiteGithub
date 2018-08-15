package com.summer.litegithub.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.summer.litegithub.R;
import com.summer.litegithub.data.NaviBean;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.adapter
 *  文件名:   NavigationAdapter
 *  创建者:   Summers
 *  创建时间: 2018/8/1419:52
 *  描述：    TODO
 */
public class NavigationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<NaviBean> mNaviBeans;
    private List<Boolean> mGroupItemStatus;

    public NavigationAdapter(Context context) {
        mContext = context;
        mGroupItemStatus = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (0 == mGroupItemStatus.size()) {
            return itemCount;
        }
        for (int i = 0; i < mNaviBeans.size(); i++) {
            itemCount++;
            if (mGroupItemStatus.get(i)) {
                itemCount += mNaviBeans.get(i).getArticles().size();
            }
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == ItemStatus.TYPE_LEVEL_0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_navi_level_0, parent, false);
            viewHolder = new ViewHolder(view);
        } else if (viewType == ItemStatus.TYPE_LEVEL_1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_navi_level_1, parent, false);
            viewHolder = new SubViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemStatus itemStatus = getItemStatusByPosition(position);
        NaviBean naviBean = mNaviBeans.get(itemStatus.getItemIndex());
        switch (itemStatus.getType()) {
            case ItemStatus.TYPE_LEVEL_0:
                ViewHolder viewHolder = (ViewHolder) holder;
                int index = itemStatus.getItemIndex();
                viewHolder.tvNaviItemTitle.setText(naviBean.getName());
                viewHolder.tvNaviItemCount.setText(String.valueOf(naviBean.getArticles().size()));
                viewHolder.llNaviItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mGroupItemStatus.get(index)) {
                            mGroupItemStatus.set(index, false);
                            viewHolder.ivNaviItem.setRotation(0);
                            notifyItemRangeRemoved(viewHolder.getAdapterPosition() + 1,
                                    naviBean.getArticles().size());
                        } else {
                            mGroupItemStatus.set(index, true);
                            viewHolder.ivNaviItem.setRotation(90);
                            notifyItemRangeInserted(viewHolder.getAdapterPosition() + 1,
                                    naviBean.getArticles().size());
                        }
                    }
                });
                break;
            case ItemStatus.TYPE_LEVEL_1:
                SubViewHolder subViewHolder = (SubViewHolder) holder;
                int subIndex = itemStatus.getSubItemIndex();
                subViewHolder.tvNaviItemSummary.setText(naviBean.getArticles().get(subIndex).getTitle());
                subViewHolder.tvNaviItemSummary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, naviBean.getArticles().get(subIndex).getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }


    public void setNaviBeans(List<NaviBean> naviBeans) {
        mNaviBeans = naviBeans;
        initItemStatus();
        notifyDataSetChanged();
    }

    private void initItemStatus() {
        for (int i = 0; i < mNaviBeans.size(); i++) {
            mGroupItemStatus.add(false);
        }
    }

    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        int itemCount = 0;
        int i;
        for (i = 0; i < mGroupItemStatus.size(); i++) {
            if (itemCount == position) {
                itemStatus.setType(ItemStatus.TYPE_LEVEL_0);
                itemStatus.setItemIndex(i);
                break;
            } else if (itemCount > position) {
                itemStatus.setType(ItemStatus.TYPE_LEVEL_1);
                itemStatus.setItemIndex(i - 1);
                int temp = (itemCount - mNaviBeans.get(i - 1).getArticles().size());
                itemStatus.setSubItemIndex(position - temp);
                break;
            }
            itemCount++;
            if (mGroupItemStatus.get(i)) {
                itemCount += mNaviBeans.get(i).getArticles().size();
            }
        }
        if (i >= mGroupItemStatus.size()) {
            itemStatus.setType(ItemStatus.TYPE_LEVEL_1);
            itemStatus.setItemIndex(i - 1);
            itemStatus.setSubItemIndex(position - (itemCount - mNaviBeans.get(i - 1).getArticles().size()));
        }
        return itemStatus;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llNaviItem;
        TextView tvNaviItemTitle;
        TextView tvNaviItemCount;
        ImageView ivNaviItem;

        public ViewHolder(View itemView) {
            super(itemView);
            llNaviItem = (LinearLayout) itemView.findViewById(R.id.ll_navi_item);
            tvNaviItemTitle = (TextView) itemView.findViewById(R.id.tv_navi_item_title);
            tvNaviItemCount = (TextView) itemView.findViewById(R.id.tv_navi_item_count);
            ivNaviItem = (ImageView) itemView.findViewById(R.id.iv_navi_item);
        }
    }

    class SubViewHolder extends RecyclerView.ViewHolder {
        TextView tvNaviItemSummary;

        public SubViewHolder(View itemView) {
            super(itemView);
            tvNaviItemSummary = (TextView) itemView.findViewById(R.id.tv_navi_item_summary);

        }
    }

}
