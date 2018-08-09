package com.summer.litegithub.util.app;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.util.app
 *  文件名:   BottomNavigationViewHelper
 *  创建者:   Summers
 *  创建时间: 2018/8/810:55
 *  描述：    TODO
 */
public class BottomNavigationViewHelper {
    private final static String TAG = "BNViewHelper";

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView,false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.d(TAG, "Unable to get shift mode field",e);
        } catch (IllegalAccessException e) {
            Log.d(TAG, "Unable to change value of shift mode",e);
        }
    }
}
