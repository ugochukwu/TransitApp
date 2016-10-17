package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.onwordiesquire.mobile.transitapp.data.model.Price;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;

import net.danlew.android.joda.JodaTimeAndroid;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            return WordUtils.capitalize(org.apache.commons.lang3.StringUtils.replace(type(), "_", " "));
        }
    }

    public String getStartTime() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            String start = this.segments().get(0).stops().get(0).dateTime();

            Date startDateTime = getDateTime(start);
            return sdf.format(startDateTime);

        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }


    }

    public String duration() {
        String start = this.segments().get(0).stops().get(0).dateTime();
        Segment segment = this.segments().get(segments().size() - 1);
        List<Stop> stops = segment.stops();
        String end = segment.stops().get(stops.size() - 1).dateTime();
        try {
            Date startDt = getDateTime(start);
            Date endDt = getDateTime(end);

           return  String.format("%d mins",Minutes.minutesBetween(new DateTime(startDt), new DateTime(endDt)).getMinutes());

        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private Date getDateTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        return sdf.parse(dateString);
    }

    public String getEndTime() {
        Segment segment = this.segments().get(segments().size() - 1);
        List<Stop> stops = segment.stops();
        String end = segment.stops().get(stops.size() - 1).dateTime();
        try {
            Date date = getDateTime(end);
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            sdf.applyPattern("h:mm a");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }

    }

    public String getFormattedPrice() {
        if (hasPrice()) {
            return String.format("%s %s", this.price().currency(), this.price().amount());
        } else {
            return "N/A";
        }
    }
}
