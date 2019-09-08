package com.manna.component.module_base.debug;

import com.manna.component.library_base.base.BaseApplication;
import com.manna.component.module_base.config.ModuleConfig;

/**
 * 组件单独运行时初始化module
 */
public class DebugApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ModuleConfig.getInstance().init(this);
    }
}
