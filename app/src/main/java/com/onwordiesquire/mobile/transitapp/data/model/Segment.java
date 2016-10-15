package com.onwordiesquire.mobile.transitapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class Segment {


    @Nullable
    public abstract String name();

    public abstract int numStops();

    @SerializedName("stops")
    @Nullable
    public abstract List<Stop> stops();

    @SerializedName("travel_mode")
    @Nullable
    public abstract String travelMode();

    @Nullable
    public abstract String color();

    @SerializedName("icon_url")
    @Nullable
    public abstract String iconUrl();

    @SerializedName("polyline")
    @Nullable
    public abstract String polyline();

    @Nullable
    public abstract String description();

    public static Builder builder() {
        return new AutoValue_Segment.Builder();
    }

    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder setNumStops(int numStops);

        abstract Builder setStops(List<Stop> stops);

        @Nullable
        abstract Builder setDescription(String description);

        abstract Builder setTravelMode(String travelMode);

        abstract Builder setColor(String color);

        abstract Builder setIconUrl(String iconUrl);

        abstract Builder setPolyline(String polyline);

        abstract Builder setName(String name);

        abstract Segment build();

    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Segment> typeAdapter(Gson gson) {
        return new AutoValue_Segment.GsonTypeAdapter(gson);
    }
}
