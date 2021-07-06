package com.easy.mvp.hook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.easy.mvp.business.SplashActivity;
import com.easy.mvp.business.main.MainActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class NeedLoginUtils {

    private static List<String> activities = new LinkedList();

    static {
        activities.add(MainActivity.class.getName());
        activities.add(SplashActivity.class.getName());
    }

    public static boolean isNeedLogin(Intent intent) {
        String goal = intent.getComponent().getClassName();
        if (activities.contains(goal))
            return false;
        return true;
    }

    public static void hookInstrumentation() throws Exception{
        Class<?> activityThread=Class.forName("android.app.ActivityThread");
        Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);
        //获取主线程对象
        Object activityThreadObject = currentActivityThread.invoke(null);

        //获取Instrumentation字段
        Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
        CustomInstrumentation customInstrumentation = new CustomInstrumentation(instrumentation);
        //替换掉原来的,就是把系统的instrumentation替换为自己的Instrumentation对象
        mInstrumentation.set(activityThreadObject, customInstrumentation);

    }

    public static class CustomInstrumentation  extends Instrumentation{
        private Instrumentation base;

        public CustomInstrumentation(Instrumentation base) {
            this.base = base;
        }

        @SuppressLint("DiscouragedPrivateApi")
        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) throws Exception {
            Method execStartActivity = null;
            execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class,
                    IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            //调用代理的Instrumentation的execStartActivity方法
            Log.e("hook", intent.getComponent().getClassName());
            return (ActivityResult) execStartActivity.invoke(base, who, contextThread,
                    token, target, intent, requestCode, options);
        }
    }

}
