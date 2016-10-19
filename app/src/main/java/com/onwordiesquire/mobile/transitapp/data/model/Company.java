package com.onwordiesquire.mobile.transitapp.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michelonwordi on 10/14/16.
 */

@AutoValue

public abstract class Company implements Parcelable{

    @SerializedName("name")
    public  abstract String name();
    @SerializedName("phone")
    public abstract String phone();

    public static Builder builder()
    {
        return new AutoValue_Company.Builder();
    }

    @AutoValue.Builder
    static abstract class Builder{
        abstract Builder setName(String name);
        abstract Builder setPhone(String phone);
        abstract Company build();
    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Company> typeAdapter(Gson gson){
        return new AutoValue_Company.GsonTypeAdapter(gson);
    }

}
