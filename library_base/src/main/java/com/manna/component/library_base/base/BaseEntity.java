package com.manna.component.library_base.base;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseEntity implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public BaseEntity() {
    }

    protected BaseEntity(Parcel in) {
    }

    public static final Creator<BaseEntity> CREATOR = new Creator<BaseEntity>() {
        @Override
        public BaseEntity createFromParcel(Parcel source) {
            return new BaseEntity(source);
        }

        @Override
        public BaseEntity[] newArray(int size) {
            return new BaseEntity[size];
        }
    };
}
