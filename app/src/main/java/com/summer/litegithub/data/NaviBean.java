package com.summer.litegithub.data;

import java.util.List;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.data
 *  文件名:   NaviBean
 *  创建者:   Summers
 *  创建时间: 2018/8/1417:59
 *  描述：    TODO
 */
public class NaviBean {
    private List<Article> articles;
    private int cid;
    private String name;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class Article {
        private String apklink;
        private String author;
        private int chapterid;
        private String chaptername;
        private boolean collect;
        private int courseid;
        private String desc;
        private String envelopepic;
        private boolean fresh;
        private int id;
        private String link;
        private String origin;
        private String projectlink;
        private int publishtime;
        private int superchapterid;
        private String superchaptername;
        private List<String> tags;
        private String title;
        private int type;
        private int userid;
        private int visible;
        private int zan;

        public String getApklink() {
            return apklink;
        }

        public void setApklink(String apklink) {
            this.apklink = apklink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterid() {
            return chapterid;
        }

        public void setChapterid(int chapterid) {
            this.chapterid = chapterid;
        }

        public String getChaptername() {
            return chaptername;
        }

        public void setChaptername(String chaptername) {
            this.chaptername = chaptername;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseid() {
            return courseid;
        }

        public void setCourseid(int courseid) {
            this.courseid = courseid;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopepic() {
            return envelopepic;
        }

        public void setEnvelopepic(String envelopepic) {
            this.envelopepic = envelopepic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getProjectlink() {
            return projectlink;
        }

        public void setProjectlink(String projectlink) {
            this.projectlink = projectlink;
        }

        public int getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(int publishtime) {
            this.publishtime = publishtime;
        }

        public int getSuperchapterid() {
            return superchapterid;
        }

        public void setSuperchapterid(int superchapterid) {
            this.superchapterid = superchapterid;
        }

        public String getSuperchaptername() {
            return superchaptername;
        }

        public void setSuperchaptername(String superchaptername) {
            this.superchaptername = superchaptername;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
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

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
