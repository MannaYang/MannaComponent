package com.manna.component.library_base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 描述：SharedPreferences操作工具类
 */
public class SPUtils {
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取字符串
     *
     * @param key      ：键
     * @param defValue ：默认值
     * @return String字符串
     */

    public static synchronized String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 保存字符串
     *
     * @param key：键
     * @param value：值
     */

    public static synchronized void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 保存字符串(需同步提交)
     *
     * @param key：键
     * @param value：值
     */
    public static synchronized void putStringWithCommit(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 保存字符串(需同步提交)
     *
     * @param key：键
     * @param value：值
     */
    public static synchronized void putStringWithCommit(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 清除缓存标签数据
     *
     * @param label：保存的字符串信息
     */
    public static synchronized void removeLabel(String label) {
        sharedPreferences.edit().remove(label).apply();
    }

    /**
     * 保存boolean标记
     *
     * @param key：键
     * @param label：值
     */
    public static synchronized void putBoolean(String key, boolean label) {
        sharedPreferences.edit().putBoolean(key, label).apply();
    }

    public static synchronized boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 保存数值
     *
     * @param key：键
     * @param value：值
     */

    public static synchronized void putInteger(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取数值
     *
     * @param key：键
     * @param defValue：默认值
     * @return int 值
     */

    public static synchronized int getInteger(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }
}
