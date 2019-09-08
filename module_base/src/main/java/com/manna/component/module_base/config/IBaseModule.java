package com.manna.component.module_base.config;

import android.app.Application;

public interface IBaseModule {
    //先初始化,此处接口也可传入priority优先级值,对应的值为 1 - 5，
    void onHighLevel(Application application);

    //延后初始化,此处接口也可传入priority优先级值,对应的值为 6 - 10，
    void onLowLevel(Application application);
}
