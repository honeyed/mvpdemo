package com.easy.mvp;

import android.app.Application;

import com.easy.mvp.hook.NeedLoginUtils;

/**
 * description:
 * author: easy
 * new date: 2021/7/6
 * version: v 1.0
 */
public class MvpApp extends Application {

    public static MvpApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }

    public static MvpApp getInstance() {
        return instance;
    }
}
