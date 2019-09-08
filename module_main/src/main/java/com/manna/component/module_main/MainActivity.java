package com.manna.component.module_main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.manna.component.library_base.base.BaseActivity;
import com.manna.component.library_base.utils.ToastUtils;
import com.manna.component.module_base.service.IMineService;
import com.manna.component.module_base.service.IOrderService;
import com.manna.component.module_base.service.ITaskService;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.util.ArrayList;
import java.util.List;

@RouterUri(path = RouterConstants.JUMP_MAIN)
public class MainActivity extends BaseActivity {

    private CustomViewPager viewPageMain;
    private int currentPosition = 0;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.module_main_activity_main;
    }

    @Override
    public void initView() {

        viewPageMain = findViewById(R.id.view_page_main);
        BottomNavigationView bottomView = findViewById(R.id.bottom_view);
        viewPageMain.setCanScroll(false);
        viewPageMain.setOffscreenPageLimit(3);
        bottomView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.tab_task) {
                currentPosition = 0;
            } else if (itemId == R.id.tab_order) {
                currentPosition = 1;
            } else if (itemId == R.id.tab_person) {
                currentPosition = 2;
            }
            viewPageMain.setCurrentItem(currentPosition, false);
            return true;
        });
    }

    @Override
    public void initData() {
        initFragment();
        LiveEventBus.get(RouterConstants.JUMP_MAIN, String.class)
                .observeSticky(this, ToastUtils::showToast);
    }

    /**
     * 初始化加载fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        //任务
        ITaskService taskService = Router.getService(ITaskService.class, "/task_fragment");
        //订单
        IOrderService orderService = Router.getService(IOrderService.class, "/order_fragment");
        //我的
        IMineService mineService = Router.getService(IMineService.class, "/mine_fragment");

        if (taskService == null) {
            //单独运行main组件,无法获取fragment组件实例,正常使用时应与需要加载的组件集成使用
            ToastUtils.showToast("单独运行main组件,无法获取fragment组件实例");
            return;
        }
        Fragment taskFragment = taskService.provideInstance();
        Fragment orderFragment = orderService.provideInstance();
        Fragment personFragment = mineService.provideInstance();

        fragmentList.add(taskFragment);
        fragmentList.add(orderFragment);
        fragmentList.add(personFragment);

        viewPageMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }
}
