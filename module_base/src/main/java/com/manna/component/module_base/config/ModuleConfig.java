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

    //初始化组件-优先,可对应priority阈值 1 - 5
    public void initHighLevel(@Nullable Application application) {
        for (String moduleInitName : ModuleConstant.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IBaseModule init = (IBaseModule) clazz.newInstance();
                //调用初始化方法
                init.onHighLevel(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //初始化组件-延后,可对应priority阈值 6 - 10
    public void initLowLevel(@Nullable Application application) {
        for (String moduleInitName : ModuleConstant.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IBaseModule init = (IBaseModule) clazz.newInstance();
                //调用初始化方法
                init.onLowLevel(application);
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
