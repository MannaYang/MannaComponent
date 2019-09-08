package com.manna.component.module_base.service;

import android.support.v4.app.Fragment;

public interface IOrderService {
    String taskName = "IOrderService:作用于跨组件加载fragment";

    Fragment provideInstance();
}
