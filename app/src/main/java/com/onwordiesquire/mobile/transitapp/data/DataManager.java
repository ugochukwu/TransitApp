package com.onwordiesquire.mobile.transitapp.data;

import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.data.source.JsonRoutesDataSource;

import java.util.List;

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


    public Observable<AvailableRoutes> loadRoutesData() {
        if (memoryCache == null) {
            Timber.i("Hit Disk");

//            return jsonRoutesDataSource.loadRoutesData().
//                    flatMap(availableRoutes -> {
//                        return Observable.just(availableRoutes).doOnNext(
//                                availableRoutes1 -> {
//                                    memoryCache = availableRoutes1;
//                                    Timber.i("Store in cache");
//                                }
//                        );
//                    });
            return jsonRoutesDataSource.getAvailableRoutes().doOnNext(availableRoutes -> {
                memoryCache = availableRoutes;
                Timber.i("Store in cache");
            });
        } else {
            Timber.i("Hit Cache");

            return Observable.just(memoryCache);
        }
    }

    public Observable<Route> getAvailableRoutes() {
        return loadRoutesData().flatMap(availableRoutes -> {
            return Observable.from(availableRoutes.routes());
        });
    }


    public Observable<Stop> getStartPoints() {

        return loadRoutesData()
                .flatMap(availableRoutes ->
                {
                    return Observable.from(availableRoutes.routes());
                })
                .flatMap(route -> {
                    return Observable.from(route.segments()).first();
                })
                .map(segment -> {
                    return segment.stops().get(0);
                });


    }

    public Observable<Stop> getEndPoints() {
        return loadRoutesData()
                .flatMap(availableRoutes ->
                {
                    return Observable.from(availableRoutes.routes());
                })
                .flatMap(route -> {
                    return Observable.from(route.segments()).last();
                })
                .map(segment -> {
                    return segment.stops().get(segment.stops().size() - 1);
                });

    }

    public Observable<Route> getAvailableRoutesForPoints(Stop start, Stop stop) {
        return loadRoutesData().flatMap(availableRoutes -> {

            return Observable.from(availableRoutes.routes());
        }).filter(route -> {
            return isStopFirst(route.segments(), start);
        });
    }

    private boolean isStopFirst(List<Segment> segments, Stop stop) {
        if (segments != null) {
            return segments.get(0).stops().get(0).equals(stop);
        } else {
            return false;
        }
    }
}
