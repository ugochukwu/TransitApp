package com.onwordiesquire.mobile.transitapp.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class Route implements Parcelable{

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
   public static abstract class Builder {
       public abstract Builder setType(String type);

        public abstract Builder setProvider(String provider);

        public abstract Builder setSegments(List<Segment> segments);

        public abstract Builder setPrice(Price price);

        public abstract Route build();

    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Route> typeAdapter(Gson gson) {
        return new AutoValue_Route.GsonTypeAdapter(gson);
    }


}
