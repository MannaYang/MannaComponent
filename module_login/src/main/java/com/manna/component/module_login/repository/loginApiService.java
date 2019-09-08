package com.manna.component.module_login.repository;

import com.manna.component.library_base.base.BaseResult;
import com.manna.component.module_login.model.entity.LoginResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface loginApiService {
    @POST("query/user")
    Call<BaseResult<LoginResult>> login(@Body Map<String, Object> params);
}
