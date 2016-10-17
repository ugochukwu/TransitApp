package com.onwordiesquire.mobile.transitapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class Route {

    @Nullable
    public abstract String type();

    @Nullable
    public abstract String provider();

    @Nullable
    public abstract List<Segment> segments();

    @Nullable
    public abstract Price price();

    public static Builder builder() {
        return new AutoValue_Route.Builder();
    }


    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder setType(String type);

        abstract Builder setProvider(String provider);

        abstract Builder setSegments(List<Segment> segments);

        abstract Builder setPrice(Price price);

        abstract Route build();

    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Route> typeAdapter(Gson gson) {
        return new AutoValue_Route.GsonTypeAdapter(gson);
    }


}
