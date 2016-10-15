package com.onwordiesquire.mobile.transitapp.util;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * built according to
 *
 * @link http://kylewbanks.com/blog/String-Date-Parsing-with-GSON-UTC-Time-Zone
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    private static final String TAG = DateDeserializer.class.getSimpleName();

    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String date = element.getAsString();

        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yy hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            Log.e(TAG, "Failed to parse Date due to:", e);
            return null;
        }
    }
}