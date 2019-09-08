package com.manna.component.module_login.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.manna.component.library_base.base.BaseCallback;
import com.manna.component.module_login.model.entity.LoginResult;
import com.manna.component.module_login.repository.ILoginDataSource;
import com.manna.component.module_login.repository.LoginDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录ViewModel，如果需要进一步解耦，直接把数据处理再封装一层，当前类只负责数据、状态传输
 */
public class ViewModelLogin extends LoginDataSource implements ILoginDataSource {

    private MutableLiveData<LoginResult> users;

    public ViewModelLogin(@NonNull Application application) {
        super(application);
    }

    public LiveData<LoginResult> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    @Override
    public void getUserInfo(String userName, String password) {
//        showLoading();加载中
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        getApiService().login(params).enqueue(new BaseCallback<LoginResult>() {
            @Override
            public void onSuccess(int code, String msg, LoginResult obj) {
//                showLoadSuccess();加载成功
                if (obj == null) {
//                    showLoadEmpty();显示空状态
                    return;
                }
                users.setValue(obj);
            }

            @Override
            public void onError(int errorType) {
//                showLoadFailed(errorType);提示错误消息
                users.setValue(new LoginResult("userName","password"));
            }
        });
    }
}
