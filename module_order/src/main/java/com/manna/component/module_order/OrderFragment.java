package com.manna.component.module_order;

import android.support.v4.app.Fragment;

import com.manna.component.library_base.base.BaseFragment;
import com.manna.component.module_base.service.IOrderService;
import com.sankuai.waimai.router.annotation.RouterProvider;
import com.sankuai.waimai.router.annotation.RouterService;

@RouterService(interfaces = IOrderService.class, key = "/order_fragment", singleton = true)
public class OrderFragment extends BaseFragment implements IOrderService {

    @Override
    public int getLayoutId() {
        return R.layout.module_order_fragment_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public Fragment provideInstance() {
        return getInstance();
    }

    // 使用注解声明该方法是一个Provider
    @RouterProvider
    public static OrderFragment getInstance() {
        return new OrderFragment();
    }
}
