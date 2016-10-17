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

    public abstract double lng();

    @SerializedName("datetime")
    public abstract String dateTime();

    @Nullable
    public abstract Object properties();

    @Nullable
    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Stop.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLng(double lng);

        public abstract Builder setLat(double lat);

        public abstract Builder setDateTime(String dateTime);

        public abstract Builder setProperties(Object properties);

        public abstract Builder setName(String name);

        public abstract Stop build();
    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Stop> typeAdapter(Gson gson) {
        return new AutoValue_Stop.GsonTypeAdapter(gson);
    }

}
