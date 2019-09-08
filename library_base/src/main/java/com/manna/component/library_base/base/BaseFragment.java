package com.manna.component.library_base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment implements IBaseView {

    protected VM viewModel;
    protected BaseActivity activity;
    private View layout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        layout = view;
        initViewModel();
        initViewObservable();
        initParams();
        initView();
        initData();
        return view;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return layout.findViewById(id);
    }

    /**
     * 设置布局
     *
     * @return ：布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 获取fragment依赖的activity
     *
     * @return ：BaseActivity
     */
    protected BaseActivity getHostActivity() {
        return activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
    }

    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
    }

    public boolean isBackPressed() {
        return false;
    }

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM getViewModel() {
        return null;
    }

    /**
     * 创建ViewModel
     *
     * @param cls ：Class
     * @param <T> ：任意类型
     * @return  ：ViewModel
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }
}
