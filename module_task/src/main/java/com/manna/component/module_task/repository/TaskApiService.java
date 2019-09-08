package com.manna.component.module_task.repository;

import com.manna.component.library_base.base.BaseResult;
import com.manna.component.module_task.model.entity.TaskResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskApiService {
    @POST("query/task")
    Call<BaseResult<TaskResult>> getTask(@Body Map<String, Object> params);
}
