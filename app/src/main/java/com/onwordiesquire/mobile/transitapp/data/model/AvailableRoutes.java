package com.onwordiesquire.mobile.transitapp.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class AvailableRoutes implements Parcelable{

    public abstract List<Route> routes();

    @SerializedName("provider_attributes")
    public abstract ProviderAttributes providerData();

    public static Builder builder() {
        return new AutoValue_AvailableRoutes.Builder();
    }

    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder setRoutes(List<Route> routes);

        abstract Builder setProviderData(ProviderAttributes providers);

        abstract AvailableRoutes build();
    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<AvailableRoutes> typeAdapter(Gson gson) {
        return new AutoValue_AvailableRoutes.GsonTypeAdapter(gson);
    }
}
