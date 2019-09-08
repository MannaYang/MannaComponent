package com.manna.component.module_base.config;

/**
 * 每个模块的初始化,可根据实际业务初始化
 */
public class ModuleConstant {
    //基础module初始化
    private static final String BaseInit = "com.manna.component.module_base.config.BaseModule";
    //主业务模块
//    private static final String MainInit = "com.manna.component.module_main.MainModule";
    //登录模块
//    private static final String LoginInit = "com.manna.component.module_login.LoginModule";
    //任务模块
//    private static final String TaskInit = "com.manna.component.module_task.TaskModule";
    //订单模块
//    private static final String OrderInit = "com.manna.component.module_order.OrderModule";
    //用户模块
//    private static final String MineInit = "com.manna.component.module_mine.MineModule";

    public static String[] initModuleNames = {BaseInit/*, MainInit,LoginInit, TaskInit, OrderInit, MineInit*/};
}
