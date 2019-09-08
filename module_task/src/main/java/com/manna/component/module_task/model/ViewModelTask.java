package com.manna.component.module_task.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;


import com.manna.component.library_base.base.BaseCallback;
import com.manna.component.module_task.model.entity.TaskResult;
import com.manna.component.module_task.repository.ITaskDataSource;
import com.manna.component.module_task.repository.TaskDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务组件ViewModel，如果需要进一步解耦，直接把数据处理再封装一层，当前类只负责数据、状态传输
 */
public class ViewModelTask extends TaskDataSource implements ITaskDataSource {

    private MutableLiveData<TaskResult> taskResult;

    public ViewModelTask(@NonNull Application application) {
        super(application);
    }

    public LiveData<TaskResult> getTask() {
        if (taskResult == null) {
            taskResult = new MutableLiveData<>();
        }
        return taskResult;
    }

    @Override
    public void getTaskInfo(String searchStr) {
//        showLoading();
        Map<String, Object> params = new HashMap<>();
        params.put("searchStr", searchStr);
        getApiService().getTask(params).enqueue(new BaseCallback<TaskResult>() {
            @Override
            public void onSuccess(int code, String msg, TaskResult obj) {
//                showLoadSuccess();
                if (obj == null) {
//                    showLoadEmpty();
                    return;
                }
                taskResult.setValue(obj);
            }

            @Override
            public void onError(int errorType) {
//                showLoadFailed(errorType);
                taskResult.setValue(new TaskResult("taskName = 获取任务信息","taskId"));
            }
        });
    }
}
