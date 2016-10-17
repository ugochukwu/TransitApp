package com.onwordiesquire.mobile.transitapp.injection.component;

import com.onwordiesquire.mobile.transitapp.presentation.routeslist.MainActivity;
import com.onwordiesquire.mobile.transitapp.TransitApp;
import com.onwordiesquire.mobile.transitapp.injection.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by michelonwordi on 10/15/16.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);


    static final class Initializer {

        public static AppComponent init(TransitApp app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }
}
