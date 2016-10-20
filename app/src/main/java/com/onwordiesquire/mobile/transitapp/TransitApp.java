package com.onwordiesquire.mobile.transitapp;

import android.app.Application;

import com.onwordiesquire.mobile.transitapp.injection.component.AppComponent;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

/**
 * Created by michelonwordi on 10/15/16.
 */

public class TransitApp extends Application {

    static private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = AppComponent.Initializer.init(this);
        JodaTimeAndroid.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static AppComponent appComponent() {
        return appComponent;
    }
}
