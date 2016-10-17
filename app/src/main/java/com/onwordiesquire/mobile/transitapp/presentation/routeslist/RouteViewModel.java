package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.onwordiesquire.mobile.transitapp.data.model.Price;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class RouteViewModel {

    @Nullable
    abstract String type();

    @Nullable
    public abstract String provider();

    @Nullable
    public abstract List<Segment> segments();

    @Nullable
    public abstract Price price();

    public static Builder builder() {
        return new AutoValue_RouteViewModel.Builder();
    }


    @AutoValue.Builder
    static abstract class Builder {
        public abstract Builder setType(String type);

        public abstract Builder setProvider(String provider);

        public abstract Builder setSegments(List<Segment> segments);

        public abstract Builder setPrice(Price price);

        public abstract RouteViewModel build();

    }


    public boolean hasPrice() {
        return price() != null;
    }

    public String getTransportType() {
        if (TextUtils.isEmpty(type())) {
            return "Name not available";
        } else {
            return type();
        }
    }

    // TODO: 10/17/16 finish this by formatting correctly with SDF
    public String getStartTime() {
        return this.segments().get(0).stops().get(0).dateTime();
    }

    public String getFormattedPrice() {
        if (hasPrice()) {
            return String.format("%s %s", this.price().currency(), this.price().amount());
        } else {
            return "N/A";
        }
    }
}
