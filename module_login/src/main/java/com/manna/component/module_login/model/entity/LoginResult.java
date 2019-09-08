package com.manna.component.module_login.model.entity;

import android.os.Parcel;

import com.manna.component.library_base.base.BaseEntity;

public class LoginResult extends BaseEntity {

    private String flag;
    private String userId;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LoginResult(String flag, String userId) {
        this.flag = flag;
        this.userId = userId;
    }

    public LoginResult(Parcel in, String flag, String userId) {
        super(in);
        this.flag = flag;
        this.userId = userId;
    }
}
