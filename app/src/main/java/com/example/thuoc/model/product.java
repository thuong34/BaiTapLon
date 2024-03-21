package com.example.thuoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class product implements Parcelable {
    private int id;
    private String name;
    private float price;

    private int res;

    public product(int id, String name, float price, int res) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.res = res;
    }

    protected product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        res = in.readInt();
    }

    public static final Creator<product> CREATOR = new Creator<product>() {
        @Override
        public product createFromParcel(Parcel in) {
            return new product(in);
        }

        @Override
        public product[] newArray(int size) {
            return new product[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getRes() {
        return res;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeInt(res);
    }
}
