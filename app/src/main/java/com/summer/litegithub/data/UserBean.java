package com.summer.litegithub.data;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.data.login
 *  文件名:   UserBean
 *  创建者:   Summers
 *  创建时间: 2018/7/1619:42
 *  描述：    TODO
 */
public class UserBean {
    private int id;
    private String username;
    private String password;

    public UserBean() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
