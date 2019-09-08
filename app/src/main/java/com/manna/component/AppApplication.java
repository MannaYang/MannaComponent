package com.manna.component;

import com.manna.component.library_base.base.BaseApplication;
import com.manna.component.module_base.config.ModuleConfig;

/**
 * 主module初始化Application
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件
        ModuleConfig.getInstance().init(this);
    }
}
