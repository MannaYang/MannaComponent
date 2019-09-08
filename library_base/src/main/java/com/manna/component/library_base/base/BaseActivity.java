package com.manna.component.library_base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;
import com.manna.component.library_base.R;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity implements IBaseView {

    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this,
                R.color.bg_status_bar), true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initViewModel();
        initViewObservable();
        initView();
        initParams();
        initData();
    }

    @Override
    public void initParams() {

    }

    @Override
    public void initData() {

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


    @Override
    public void initViewObservable() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
    }

    /**
     * 初始化ViewModel
     *
     * 继承BaseViewModel的ViewModel
     */
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

    public VM getViewModel() {
        return null;
    }

    /**
     * 创建ViewModel
     *
     * @param cls :Class
     * @param <T> :任意类型
     * @return  ：ViewModel
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }

    /**
     * WMRouter跳转
     *
     * @param context：上下文
     * @param uri：跳转路径
     * @param extraName：参数名称
     * @param parcelable：参数值
     */
    public void startUri(Context context, String uri, String extraName, Parcelable parcelable) {
        Router.startUri(new DefaultUriRequest(context, uri)
                .putExtra(extraName, parcelable)
                .overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim));
    }

    /**
     * WMRouter跳转
     *
     * @param context：上下文
     * @param uri：跳转路径
     */
    public void startUri(Context context, String uri) {
        Router.startUri(context, uri);
    }
}
