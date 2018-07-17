package com.summer.litegithub.model.cookie;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.summer.litegithub.base.App.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.model.cookie
 *  文件名:   PersistentCookieStore
 *  创建者:   Summers
 *  创建时间: 2018/7/1710:49
 *  描述：    TODO
 */
class PersistentCookieStore {
    private final static String TAG = "PersistentCookieStore";
    private final static String COOKIE_PREFS = "cookie_prefs";
    private final Map<String, ConcurrentMap<String, Cookie>> mCookies;
    private final SharedPreferences mCookiePrefs;

    public PersistentCookieStore() {
        mCookiePrefs = MyApplication.getInstance().getSharedPreferences(COOKIE_PREFS, 0);
        mCookies = new HashMap<>();
        Map<String, ?> prefsMap = mCookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = mCookiePrefs.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodedCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!mCookies.containsKey(entry.getKey())) {
                            mCookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        mCookies.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);

        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!mCookies.containsKey(url.host())) {
                mCookies.put(url.host(), new ConcurrentHashMap<String, Cookie>());
            }
            mCookies.get(url.host()).put(name, cookie);
        } else {
            if (mCookies.containsKey(url.host())) {
                mCookies.get(url.host()).remove(name);
            }
        }

        SharedPreferences.Editor editor = mCookiePrefs.edit();
        editor.putString(url.host(), TextUtils.join(",", mCookies.get(url.host()).keySet()));
        editor.putString(name, encodeCookie(new OkHttpCookies(cookie)));
        editor.apply();
    }

    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        if (mCookies.containsKey(url.host())) {
            ret.addAll(mCookies.get(url.host()).values());
        }
        return ret;
    }

    public boolean removeAll() {
        SharedPreferences.Editor editor = mCookiePrefs.edit();
        editor.clear();
        editor.apply();
        mCookies.clear();
        return true;
    }

    public boolean remove(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        if (mCookies.containsKey(url.host()) && mCookies.get(url.host()).containsKey(name)) {
            mCookies.get(url.host()).remove(name);
            SharedPreferences.Editor editor = mCookiePrefs.edit();
            if (mCookiePrefs.contains(name)) {
                editor.remove(name);
            }
            editor.putString(url.host(), TextUtils.join(",", mCookies.get(url.host()).keySet()));
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : mCookies.keySet()) {
            ret.addAll(mCookies.get(key).values());
        }
        return ret;
    }

    private String encodeCookie(OkHttpCookies okHttpCookies) {
        if (okHttpCookies == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(okHttpCookies);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return byteArrayToHexString(outputStream.toByteArray());
    }

    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xFF;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    private Cookie decodedCookie(String encodedCookie) {
        byte[] bytes = hexStringToByteArray(encodedCookie);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            cookie =((OkHttpCookies) objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    private byte[] hexStringToByteArray(String encodedCookie) {
        int len = encodedCookie.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(encodedCookie.charAt(i), 16) << 4)
                    + Character.digit(encodedCookie.charAt(i + 1), 16));
        }
        return data;
    }
}
