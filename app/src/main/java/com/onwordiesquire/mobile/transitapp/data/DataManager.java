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

            return jsonRoutesDataSource.getAvailableRoutes().doOnNext(availableRoutes -> {
                memoryCache = availableRoutes;
            });
        } else {

            return Observable.just(memoryCache);
        }
    }

    public Observable<Route> getAvailableRoutes() {
        return loadRoutesData().flatMap(availableRoutes -> {
            return Observable.from(availableRoutes.routes());
        });
    }


    //returns a list of start points from every segment in a route
    public Observable<Stop> getStartPoints() {

        return loadRoutesData()
                .flatMap(availableRoutes ->
                        Observable.from(availableRoutes.routes()))
                .flatMap(route -> Observable.from(route.segments()).first())
                .map(segment -> segment.stops().get(0));


    }

    //returns a list of all end points from every segment in a route
    public Observable<Stop> getEndPoints() {
        return loadRoutesData()
                .flatMap(availableRoutes ->
                        Observable.from(availableRoutes.routes()))
                .flatMap(route -> Observable.from(route.segments()).last())
                .map(segment -> segment.stops().get(segment.stops().size() - 1));

    }

    public Observable<Route> getAvailableRoutesForPoints(Stop start, Stop stop) {
        return loadRoutesData().flatMap(availableRoutes -> Observable.from(availableRoutes.routes())).filter(route -> isStopFirst(route.segments(), start));
    }

    private boolean isStopFirst(List<Segment> segments, Stop stop) {
        if (segments != null) {
            return segments.get(0).stops().get(0).equals(stop);
        } else {
            return false;
        }
    }
}
