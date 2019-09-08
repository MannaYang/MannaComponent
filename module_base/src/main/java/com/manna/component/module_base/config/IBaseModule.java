package com.manna.component.module_base.config;

import android.app.Application;

public interface IBaseModule {
    //初始化组件,此处接口也可传入priority优先级值,对应的值为 1 - 9
    void init(Application application);
}
