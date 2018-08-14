package com.summer.litegithub.data;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.data
 *  文件名:   ProjectNaviBean
 *  创建者:   Summers
 *  创建时间: 2018/8/1415:42
 *  描述：    TODO
 */
public class ProjectNaviBean {
    private List<String> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
