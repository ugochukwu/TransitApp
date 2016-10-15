package com.onwordiesquire.mobile.transitapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a stop in a route segment
 * <p>
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class Stop {

    public abstract double lat();

    public  abstract double lng();

    @SerializedName("datetime")
    public  abstract String dateTime();

    @Nullable
    public  abstract Object properties();

    @Nullable
    public  abstract String name();

    public  static Builder builder() {
        return new AutoValue_Stop.Builder();
    }

    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder setLng(double lng);

        abstract Builder setLat(double lat);

        abstract Builder setDateTime(String dateTime);

        abstract Builder setProperties(Object properties);

        abstract Builder setName(String name);

        abstract Stop build();
    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Stop> typeAdapter(Gson gson) {
        return new AutoValue_Stop.GsonTypeAdapter(gson);
    }

}
