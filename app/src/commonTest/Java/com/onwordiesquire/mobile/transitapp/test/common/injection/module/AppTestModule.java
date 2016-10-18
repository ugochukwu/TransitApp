package com.onwordiesquire.mobile.transitapp.test.common.injection.module;

import android.content.Context;

import com.onwordiesquire.mobile.transitapp.TransitApp;
import com.onwordiesquire.mobile.transitapp.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by michelonwordi on 10/18/16.
 */

@Module
public class AppTestModule {

    private final TransitApp app;

    public AppTestModule(TransitApp app) {
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

    @Provides
    @Singleton
    public DataManager provideMockDataManager()
    {
       return mock(DataManager.class);
    }

}
