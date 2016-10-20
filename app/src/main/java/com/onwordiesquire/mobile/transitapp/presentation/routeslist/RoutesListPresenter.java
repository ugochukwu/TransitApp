package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import com.onwordiesquire.mobile.transitapp.data.DataManager;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.presentation.BasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static rx.Single.just;

/**
 * Created by michelonwordi on 10/17/16.
 */
@Singleton
public class RoutesListPresenter extends BasePresenter<RoutesListMvpView> {

    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;

    @Inject
    public RoutesListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(RoutesListMvpView mvpView) {
        super.attachView(mvpView);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        mCompositeSubscription.clear();
    }

    public void loadAvailableRoutes() {
        checkViewAttached();
        Subscription subscribe = mDataManager.getAvailableRoutes()
                .map(route -> convertRouteToViewModel(route))
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(routes -> {

                            if (routes.size() == 0) {
                                getMvpView().showEmptyState();
                            }
                            getMvpView().showRoutes(routes);
                        },
                        e -> {
                            getMvpView().showErrorView();
                        });
        mCompositeSubscription.add(subscribe);
    }

    public void openRouteDetails(RouteViewModel route) {
        checkViewAttached();
        if (route != null) {
            getMvpView().showRouteDetail(route);
        }


    }

    private RouteViewModel convertRouteToViewModel(Route route) {
        RouteViewModel rvm = RouteViewModel.builder()
                .setPrice(route.price())
                .setProvider(route.provider())
                .setSegments(route.segments())
                .setType(route.type())
                .build();
        return rvm;
    }

}
