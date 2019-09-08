package com.manna.component.library_base.utils;

import android.net.ParseException;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.manna.component.library_base.base.BaseApplication;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 描述：提示框
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * 显示提示消息,默认短时间
     *
     * @param msg：消息内容
     */
    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 可选择时间长短提示
     *
     * @param msg：消息内容
     * @param timeTag：显示长时间、短时间
     */
    public static void showToastTimeTag(String msg, int timeTag) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), msg, timeTag);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 回调失败toast
     *
     * @param error：Throwable 对象
     */
    public static void showErrorToast(Throwable error) {
        String msg;
        if (error instanceof ParseException || error instanceof JSONException) {
            msg = "解析异常";
        } else if (error instanceof SocketTimeoutException) {
            msg = "网络连接超时";
        } else if (error instanceof ConnectException) {
            msg = "网络连接失败,请检查网络";
        } else if (error instanceof UnknownHostException) {
            msg = "无法连接到服务器";
        } else {
            msg = "服务器异常";
        }
        showToast(msg);
    }
}
