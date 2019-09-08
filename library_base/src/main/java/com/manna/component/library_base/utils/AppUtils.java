package com.manna.component.library_base.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 获取ApplicationContext
 */
public final class AppUtils {

    private static final String ACTIVITY_THREAD_PATH = "android.app.ActivityThread";
    @SuppressLint("StaticFieldLeak")
    private static volatile Application sInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * 反射获取ApplicationContext
     *
     * @return ：Application
     */
    public static Application getApplicationContext() {
        if (sInstance == null) {
            synchronized (AppUtils.class) {
                if (sInstance == null) {
                    try {
                        sInstance = (Application) Class.forName(ACTIVITY_THREAD_PATH)
                                .getMethod("currentApplication")
                                .invoke(null, (Object[]) null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化Context
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        AppUtils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先初始化Application");
    }
}
