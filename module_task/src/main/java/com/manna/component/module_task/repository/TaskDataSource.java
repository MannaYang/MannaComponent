package com.manna.component.module_task.repository;

import android.app.Application;
import android.support.annotation.NonNull;

import com.manna.component.library_base.base.BaseViewModel;
import com.manna.component.library_base.http.RetrofitManage;


/**
 * RemoteDataSource
 */
public class TaskDataSource extends BaseViewModel {

    private static final String BASE_URL = "http://172.16.102.55:8080/";
    private static final String TEST_Url = "TEST_URL";
    private static final String TEMP_Url = "TEMP_URL";
    private static final String LOCAL_Url = "LOCAL_URL";

    public TaskDataSource(@NonNull Application application) {
        super(application);
    }

    public TaskApiService getApiService() {
        return RetrofitManage.getInstance().getApiService(TaskApiService.class, BASE_URL);
    }
}
