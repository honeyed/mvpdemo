package com.easy.mvp.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/8/23
 * version: v 1.0
 */
public class Any {

    public static void asyncTask (AsyncTask asyncTask) {
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Object o = asyncTask.execute();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (o != null)
                            asyncTask.OnMessageResponse(o);
                    }
                });
            }
        });
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

    // 创建数组型缓冲等待队列
    private static BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(100);
    private static ThreadPoolExecutor cachedThreadPool = new ThreadPoolExecutor(4, 6, 60, TimeUnit.SECONDS, bq);
}
