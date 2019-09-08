package com.manna.component.library_base.http;

import com.manna.component.library_base.http.interceptor.FastJsonConvertFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitManage {
    private static final int DEFAULT_SECONDS = 8;

    private RetrofitManage() {
    }

    private static class RetrofitManageInstance {
        private static final RetrofitManage RETROFIT_MANAGE = new RetrofitManage();
    }

    public static RetrofitManage getInstance() {
        return RetrofitManageInstance.RETROFIT_MANAGE;
    }

    /**
     * 创建retrofit
     *
     * @return Retrofit
     */
    private Retrofit createRetrofit(String baseUrl) {

        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new FastJsonConvertFactory())
                .client(httpClient)
                .build();
    }

    /**
     * 根据各模块业务接口 获取不同的retrofit service接口对象,单工程项目可以直接定义BaseService统一管理
     */
    public <T> T getApiService(Class<T> cls, String baseUrl) {
        return createRetrofit(baseUrl).create(cls);
    }
}
