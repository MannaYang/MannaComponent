package com.manna.component.module_login.view;

import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.manna.component.library_base.base.BaseActivity;
import com.manna.component.library_base.utils.SPUtils;
import com.manna.component.module_login.R;
import com.manna.component.module_login.model.ViewModelLogin;
import com.manna.component.module_login.model.entity.LoginResult;
import com.sankuai.waimai.router.annotation.RouterUri;


/**
 * 登录
 */
@RouterUri(path = RouterConstants.JUMP_LOGIN)
public class LoginActivity extends BaseActivity<ViewModelLogin> {

    private ViewModelLogin viewModelLogin;

    @Override
    public int getLayoutId() {
        return R.layout.module_login_activity_login;
    }

    @Override
    public void initView() {
        viewModelLogin = viewModel;
        TextView tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(v ->
//                viewModelLogin.getUserInfo("username", "password"));请求登录接口
                saveLoginParams(null));

        //监听登录接口返回数据
        viewModelLogin.getUsers().observe(this, this::saveLoginParams);
    }


    @Override
    public void initData() {

    }

    /**
     * 保存登录参数
     *
     * @param user : LoginResult
     */
    private void saveLoginParams(LoginResult user) {
        SPUtils.putString("user_id", "user_id");

        //跳转main页面
        startUri(this, RouterConstants.JUMP_MAIN, null, null);
        LiveEventBus.get(RouterConstants.JUMP_MAIN).post("登录成功发送LiveEventBus消息");
        finish();
    }
}
