package com.onwordiesquire.mobile.transitapp;

import android.app.Application;

import com.onwordiesquire.mobile.transitapp.injection.component.AppComponent;

import timber.log.Timber;

/**
 * Created by michelonwordi on 10/15/16.
 */

public class TransitApp extends Application {

    static private AppComponent appComponent;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = AppComponent.Initializer.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static AppComponent appComponent()
    {
        return appComponent;
    }
}
