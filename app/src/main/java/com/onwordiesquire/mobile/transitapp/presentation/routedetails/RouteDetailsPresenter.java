package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.presentation.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/18/16.
 */
@Singleton
public class RouteDetailsPresenter extends BasePresenter<RouteDetailsMvpView> {

    private Segment segment;
    private Route route;

    @Inject
    public RouteDetailsPresenter() {

    }


    @Override
    public void attachView(RouteDetailsMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void loadPolyines(Route route) {
        Observable.just(route)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(selectedRoute -> {
                    return Observable.from(selectedRoute.segments());
                })
                .map(Segment::polyline)
                .toList()
                .subscribe(polylines -> {
                    getMvpView().drawPolyLinesOnMap(polylines);
                }, e -> {
                    Timber.e(e, e.getMessage());
                    getMvpView().showEmptyState();
                });

    }

    public void loadSegments(Route route) {
        checkViewAttached();
        this.route = route;
        if (route != null) {
            getMvpView().showSegmentsInBottomSheet(route.segments());
            getMvpView().drawSegmentsOnMap(route.segments());
        }
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadBounds(Route route) {
        checkViewAttached();

        //add start bounds
        List<Segment> segments = route.segments();
        Stop first = segments.get(0).stops().get(0);

        //add end bound
        List<Stop> stops = segments.get(segments.size() - 1).stops();
        Stop last = stops.get(stops.size()-1);

        getMvpView().centerMap(first,last);




    }
}
