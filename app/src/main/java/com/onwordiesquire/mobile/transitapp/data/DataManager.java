package com.onwordiesquire.mobile.transitapp.data;

import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.source.JsonRoutesDataSource;
import com.onwordiesquire.mobile.transitapp.util.JSONResourceReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/15/16.
 */
@Singleton
public class DataManager {

    private JsonRoutesDataSource jsonRoutesDataSource;
    private AvailableRoutes memoryCache = null;

    @Inject
    public DataManager(JsonRoutesDataSource jsonRoutesDataSource) {
        this.jsonRoutesDataSource = jsonRoutesDataSource;
    }


    public Observable<AvailableRoutes> getAvailableRoutes() {
        if (memoryCache == null) {
            Timber.i("Hit Disk");

//            return jsonRoutesDataSource.getAvailableRoutes().
//                    flatMap(availableRoutes -> {
//                        return Observable.just(availableRoutes).doOnNext(
//                                availableRoutes1 -> {
//                                    memoryCache = availableRoutes1;
//                                    Timber.i("Store in cache");
//                                }
//                        );
//                    });
            return jsonRoutesDataSource.getAvailableRoutes().doOnNext(availableRoutes ->{ memoryCache = availableRoutes;
            Timber.i("Store in cache");});
        } else {
            Timber.i("Hit Cache");

            return Observable.just(memoryCache);
        }
    }


}
