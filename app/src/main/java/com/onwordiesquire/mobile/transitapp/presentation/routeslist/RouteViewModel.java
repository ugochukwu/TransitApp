package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;
import com.onwordiesquire.mobile.transitapp.data.model.Price;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.util.Utilities;

import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * View Model to aid in presenting necessary route information for the respective view.
 *
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class RouteViewModel implements Parcelable{

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

    public String getTransportName() {
        if (TextUtils.isEmpty(type())) {
            return "Name not available";
        } else if (!type().equals("public_transport")) {

            return Utilities.cleanString(provider());
        } else {
            return Utilities.cleanString(type());
        }
    }

    public String getStartTime() {

        String start = this.segments().get(0).stops().get(0).dateTime();
        return Utilities.formatTime(start);

    }

    public String duration() {
        String start = this.segments().get(0).stops().get(0).dateTime();
        Segment segment = this.segments().get(segments().size() - 1);
        List<Stop> stops = segment.stops();
        String end = segment.stops().get(stops.size() - 1).dateTime();
        try {
            return Utilities.calcDuration(start, end);

        } catch (Exception e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    public Route getRoute()
    {
        return Route.builder()
                .setPrice(price())
                .setProvider(provider())
                .setSegments(segments())
                .setType(type())
                .build();
    }





    public String getEndTime() {
        Segment segment = this.segments().get(segments().size() - 1);
        List<Stop> stops = segment.stops();
        String end = segment.stops().get(stops.size() - 1).dateTime();
        return Utilities.formatTime(end);
    }

    public String getFormattedPrice() {
        if (hasPrice()) {
            return String.format("%s %s", this.price().currency(), this.price().amount());
        } else {
            return "N/A";
        }
    }
}
