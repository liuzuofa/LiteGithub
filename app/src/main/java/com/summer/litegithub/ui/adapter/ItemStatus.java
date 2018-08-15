package com.summer.litegithub.ui.adapter;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.ui.adapter
 *  文件名:   ItemStatus
 *  创建者:   Summers
 *  创建时间: 2018/8/1514:26
 *  描述：    TODO
 */
public class ItemStatus {
    public final static int TYPE_LEVEL_0 = 0;
    public final static int TYPE_LEVEL_1 = 1;
    private int mType;
    private int mItemIndex;//一级索引
    private int mSubItemIndex = -1;//二级索引

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getItemIndex() {
        return mItemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.mItemIndex = itemIndex;
    }

    public int getSubItemIndex() {
        return mSubItemIndex;
    }

    public void setSubItemIndex(int subItemIndex) {
        this.mSubItemIndex = subItemIndex;
    }
}
