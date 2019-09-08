package com.manna.component.library_base.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 基础解析类
 *
 * @param <T> 实体bean
 */
public class BaseResult<T> implements Parcelable {

    //code
    private int code;
    //message
    private String msg;
    //token
    private String token;
    //实体bean
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeString(this.token);
        dest.writeString(data.getClass().getName());
        dest.writeParcelable((Parcelable) this.data, flags);
    }

    public BaseResult() {
    }

    protected BaseResult(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.token = in.readString();
        String dataName = in.readString();
        try {
            this.data = in.readParcelable(Class.forName(dataName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<BaseResult> CREATOR = new Creator<BaseResult>() {
        @Override
        public BaseResult createFromParcel(Parcel source) {
            return new BaseResult(source);
        }

        @Override
        public BaseResult[] newArray(int size) {
            return new BaseResult[size];
        }
    };
}
