package com.manna.component.module_task.view;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.manna.component.library_base.base.BaseFragment;
import com.manna.component.module_base.service.ITaskService;
import com.manna.component.module_task.R;
import com.manna.component.module_task.model.ViewModelTask;
import com.sankuai.waimai.router.annotation.RouterProvider;
import com.sankuai.waimai.router.annotation.RouterService;

@RouterService(interfaces = ITaskService.class, key = "/task_fragment", singleton = true)
public class TaskFragment extends BaseFragment<ViewModelTask> implements ITaskService {

    private TextView tvContent;
    private ViewModelTask viewModelTask;

    @Override
    public int getLayoutId() {
        return R.layout.module_task_fragment_task;
    }

    @Override
    public void initView() {
        tvContent = findViewById(R.id.tv_content);

        viewModelTask = viewModel;
        viewModelTask.getTask().observe(this, taskResult -> {
            if (taskResult == null) {
                return;
            }
            String liveDataStr = "LiveData更新数据";
            tvContent.setText(liveDataStr);
        });
    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> viewModelTask.getTaskInfo("查询任务信息"), 2000);
    }

    @Override
    public Fragment provideInstance() {
        return getInstance();
    }

    // 使用注解声明该方法是一个Provider
    @RouterProvider
    public static TaskFragment getInstance() {
        return new TaskFragment();
    }
}
