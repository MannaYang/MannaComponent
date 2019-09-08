package com.manna.component.module_base.service;

import android.support.v4.app.Fragment;

public interface ITaskService {
    String taskName = "ITaskService:作用于跨组件加载fragment";

    Fragment provideInstance();
}
