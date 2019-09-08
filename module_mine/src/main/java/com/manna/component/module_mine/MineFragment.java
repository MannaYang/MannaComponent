package com.manna.component.module_mine;

import android.support.v4.app.Fragment;

import com.manna.component.library_base.base.BaseFragment;
import com.manna.component.module_base.service.IMineService;
import com.sankuai.waimai.router.annotation.RouterProvider;
import com.sankuai.waimai.router.annotation.RouterService;

@RouterService(interfaces = IMineService.class, key = "/mine_fragment", singleton = true)
public class MineFragment extends BaseFragment implements IMineService {

    @Override
    public int getLayoutId() {
        return R.layout.module_mine_fragment_person;
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
    public static MineFragment getInstance() {
        return new MineFragment();
    }
}
