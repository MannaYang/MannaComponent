package com.manna.component.module_base.config;

import android.app.Application;
import android.support.annotation.Nullable;

public class ModuleConfig {
    private static final ModuleConfig INSTANCE = new ModuleConfig();

    public static ModuleConfig getInstance() {
        return INSTANCE;
    }

    private ModuleConfig() {
    }

    //初始化组件,可对应priority阈值 1 - 9
    public void init(@Nullable Application application) {
        for (String moduleInitName : ModuleConstant.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IBaseModule iBaseModule = (IBaseModule) clazz.newInstance();
                //调用初始化方法
                iBaseModule.init(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
