package com.manna.component.module_base.config;

import android.app.Application;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.manna.component.library_base.utils.SPUtils;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.core.Debugger;

import static com.manna.component.library_base.base.BaseApplication.getInstance;

/**
 * 业务组件初始化
 */
public class BaseModule implements IBaseModule {
    @Override
    public void onHighLevel(Application application) {
        initWMRouter();
        //初始化SP
        SPUtils.init(getInstance());
        //初始化WMRouter日志
        initLogger();
        //初始化LiveDataBus
        initLiveDataBus();
    }

    @Override
    public void onLowLevel(Application application) {

    }

    /**
     * 初始化路由组件
     */
    private void initWMRouter() {
        // 创建RootHandler
        DefaultRootUriHandler rootHandler = new DefaultRootUriHandler(getInstance());
        // 初始化
        Router.init(rootHandler);
    }

    /**
     * 初始化WMRouter日志
     */
    private void initLogger() {
        // 自定义Logger
//        DefaultLogger logger = new DefaultLogger() {
//            @Override
//            protected void handleError(Throwable t) {
//                super.handleError(t);
//                // 此处上报Fatal级别的异常
//            }
//        };

        // 设置Logger
//        Debugger.setLogger(logger);

        // Log开关，建议测试环境下开启，方便排查问题。
        Debugger.setEnableLog(true);

        // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
        Debugger.setEnableDebug(true);
    }

    /**
     * 初始化LiveDataBus事件总线
     */
    private void initLiveDataBus() {
        LiveEventBus.config().supportBroadcast(getInstance()).
                lifecycleObserverAlwaysActive(true).autoClear(false);
    }
}
