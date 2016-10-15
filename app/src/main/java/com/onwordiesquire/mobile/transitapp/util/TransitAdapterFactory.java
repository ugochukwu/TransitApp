package com.onwordiesquire.mobile.transitapp.util;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by michelonwordi on 10/15/16.
 */
@GsonTypeAdapterFactory
public abstract class TransitAdapterFactory implements TypeAdapterFactory{

    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_TransitAdapterFactory();
    }
}
