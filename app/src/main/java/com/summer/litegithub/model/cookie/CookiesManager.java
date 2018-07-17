package com.summer.litegithub.model.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.model.cookie
 *  文件名:   CookiesManager
 *  创建者:   Summers
 *  创建时间: 2018/7/1710:45
 *  描述：    TODO
 */
public class CookiesManager implements CookieJar {
    private final static PersistentCookieStore cookieStore = new PersistentCookieStore();
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie: cookies) {
                cookieStore.add(url,cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    public static void clearAllCookies() {
        cookieStore.removeAll();
    }

    public static boolean clearCookies(HttpUrl url,Cookie cookie) {
        return cookieStore.remove(url,cookie);
    }

    public static List<Cookie> getCookies() {
        return cookieStore.getCookies();
    }
}
