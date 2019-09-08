package com.manna.component.module_base.config;

/**
 * 每个模块的初始化,可根据实际业务初始化
 */
public class ModuleConstant {
    //基础module初始化
    private static final String BaseInit = "com.manna.component.module_base.config.BaseModule";
    //主业务模块
//    private static final String MainInit = "com.manna.component.main.MainModuleInit";
    //登录验证模块
//    private static final String SignInit = "com.manna.component.sign.SignModuleInit";
    //首页业务模块
//    private static final String HomeInit = "com.manna.component.home.HomeModuleInit";
    //工作业务模块
//    private static final String WorkInit = "com.manna.component.work.WorkModuleInit";
    //消息业务模块
//    private static final String MsgInit = "com.manna.component.msg.MsgModuleInit";
    //用户业务模块
//    private static final String UserInit = "com.manna.component.user.UserModuleInit";

    public static String[] initModuleNames = {BaseInit/*, MainInit,SignInit, HomeInit, WorkInit, MsgInit,UserInit*/};
}
