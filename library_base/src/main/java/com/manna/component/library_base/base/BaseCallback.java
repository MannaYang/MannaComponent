package com.manna.component.library_base.base;

import com.manna.component.library_base.utils.SPUtils;
import com.manna.component.library_base.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * retrofit2回调处理
 */
public abstract class BaseCallback<T> implements Callback<BaseResult<T>> {

    private static final String ERROR_MSG = "服务器异常";
    private static final int TOKEN_LOSE = 10099;
    private static final int TOKEN_TAKE_UP = 10098;
    private static final int ERROR_NETWORK_UNREACHABLE = 10097;
    private static final int ERROR_SERVER_PARSE_FAILED = 10096;
    private static final String TOKEN = "token";

    @Override
    public void onResponse(Call<BaseResult<T>> call, Response<BaseResult<T>> response) {
        BaseResult<T> result = response.body();
        if (result == null) {
            //500  404错误
            onError(ERROR_SERVER_PARSE_FAILED);
            ToastUtils.showToast(ERROR_MSG);
        } else if (result.getCode() == TOKEN_LOSE || result.getCode() == TOKEN_TAKE_UP) {
            SPUtils.putStringWithCommit(TOKEN, "");
//            LiveEventBus.get().with().post("");
        } else {
            onSuccess(result.getCode(), result.getMsg(), result.getData());
        }
    }

    @Override
    public void onFailure(Call<BaseResult<T>> call, Throwable e) {
        ToastUtils.showErrorToast(e);
        if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            onError(ERROR_NETWORK_UNREACHABLE);
        } else {
            onError(ERROR_SERVER_PARSE_FAILED);
        }
    }

    /**
     * 成功回调
     *
     * @param code：code
     * @param msg:后台返回消息
     * @param obj:data参数
     */
    public abstract void onSuccess(int code, String msg, T obj);

    /**
     * 失败回调
     *
     * @param errorType :错误类型,作用于页面状态切换
     */
    public abstract void onError(int errorType);
}
