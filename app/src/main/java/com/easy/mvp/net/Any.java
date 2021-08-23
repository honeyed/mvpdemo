package com.easy.mvp.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/8/23
 * version: v 1.0
 */
public class Any {

    public static void asyncTask (AsyncTask asyncTask) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Object o = asyncTask.execute();
                handler.sendEmptyMessage(1);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        asyncTask.OnMessageResponse(o);
                    }
                });
            }
        }).start();
    }


    public interface AsyncTask<T> {
        T execute();
        void OnMessageResponse(T t);
    }

    private static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
