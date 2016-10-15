package com.onwordiesquire.mobile.transitapp.injection.modules;

import android.app.Application;
import android.content.Context;

import com.onwordiesquire.mobile.transitapp.TransitApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelonwordi on 10/15/16.
 */
@Module
public class AppModule {


    private final TransitApp app;

    public AppModule(TransitApp app) {
        this.app = app;
    }


    @Provides
    @Singleton
    public TransitApp provideApp() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }

}
