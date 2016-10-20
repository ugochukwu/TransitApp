package com.onwordiesquire.mobile.transitapp.util;

import com.onwordiesquire.mobile.transitapp.R;

import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michelonwordi on 10/18/16.
 */

public class Utilities {

    /**
     * Remove underscore from a string and replace with a space
     *
     * @param name
     * @return
     */
    public static String cleanString(String name) {
        return WordUtils.capitalize(org.apache.commons.lang3.StringUtils.replace(name, "_", " "));
    }

    public static String formatTime(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            sdf.applyPattern("h:mm a");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }

    }

    /**
     * Calculate duration between string start and stop times
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static String calcDuration(String start, String end) throws ParseException {
        Date startDt = getDateTime(start);
        Date endDt = getDateTime(end);

        return String.format("%d mins", Minutes.minutesBetween(new DateTime(startDt), new DateTime(endDt)).getMinutes());
    }

    /**
     * Convert string into date String into date representation
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date getDateTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        return sdf.parse(dateString);
    }

    /**
     * returns the appropriate resource id based on the provided url
     *
     * @param segmentIconURl
     * @return
     */
    public static int bindIconResource(String segmentIconURl) {
        int resID;
        switch (segmentIconURl) {
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/subway.svg":
                resID = R.drawable.ic_subway;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg":
                resID = R.drawable.ic_walking;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/bus.svg":
                resID = R.drawable.ic_bus;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/change.svg":
                resID = R.drawable.ic_change;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/driving.svg":
                resID = R.drawable.ic_car;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/parking.svg":
                resID = R.drawable.ic_park;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/setup.svg":
                resID = R.drawable.ic_setup;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/bike_sharing.svg":
                resID = R.drawable.ic_bike_sharing;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/cycling.svg":
                resID = R.drawable.ic_cycling;
                break;
            case "https://d3m2tfu2xpiope.cloudfront.net/vehicles/taxi.svg":
                resID = R.drawable.ic_taxii;
                break;
            default:
                resID = R.drawable.ic_not_available;

        }

        return resID;
    }
}
