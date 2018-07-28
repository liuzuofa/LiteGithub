package com.summer.litegithub.data;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.data
 *  文件名:   BannerBean
 *  创建者:   Summers
 *  创建时间: 2018/7/2417:42
 *  描述：    TODO
 */
public class BannerBean {

    /*"desc": "",
    "id": 6,
    "imagePath": "http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
    "isVisible": 1,
    "order": 1,
    "title": "我们新增了一个常用导航Tab~",
    "type": 0,
    "url": "http://www.wanandroid.com/navi"*/

    private int id;
    private String imagePath;
    private boolean isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
