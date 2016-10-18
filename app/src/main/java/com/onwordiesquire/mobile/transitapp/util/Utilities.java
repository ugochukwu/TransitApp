package com.onwordiesquire.mobile.transitapp.util;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by michelonwordi on 10/18/16.
 */

public class Utilities {

    /**
     * Remove underscore from a string and replace with a space
     * @param name
     * @return
     */
    public static String cleanString(String name)
    {
       return WordUtils.capitalize(org.apache.commons.lang3.StringUtils.replace(name, "_", " "));
    }
}
