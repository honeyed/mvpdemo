package com.easy.mvp;

import android.app.Application;

import com.easy.mvp.hook.NeedLoginUtils;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class MvpApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            NeedLoginUtils.hookInstrumentation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
