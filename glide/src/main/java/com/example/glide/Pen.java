package com.example.glide;

import android.os.Parcel;
import android.os.Parcelable;

public class Pen implements Parcelable {
    private String color;
    private int size;

    //从序列化的对象中创建原始对象
    protected Pen(Parcel in){
        color = in.readString();
        size = in.readInt();
    }

    public static final Creator<Pen> CREATOR = new Creator<Pen>() {
        //用于从序列化对象中创建原始对象
        @Override
        public Pen createFromParcel(Parcel source) {
            return new Pen(source);
        }

        //用于创建指定长度的原始对象数组
        @Override
        public Pen[] newArray(int size) {
            return new Pen[size];
        }
    };


    @Override
    public int describeContents() {
        //标记为Parcelable.CONTENTS_FILE_DESCRIPTOR
        return 0;
    }


    //目前只返回两个值，一个是0，一个是CONTENTS_FILE_DESCRIPTOR，0的话，就是普通parcelable。
    //CONTENTS_FILE_DESCRIPTOR的话，就是parcelable里面含有文件描述符。
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeInt(size);
    }
}
