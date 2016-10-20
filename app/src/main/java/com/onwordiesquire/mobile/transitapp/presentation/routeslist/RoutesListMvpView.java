package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import com.onwordiesquire.mobile.transitapp.presentation.MvpView;

import java.util.List;

/**
 * Created by michelonwordi on 10/17/16.
 * <p>
 * Defines the contract for a RouteListMvpView
 */

public interface RoutesListMvpView extends MvpView {

    public void showRoutes(List<RouteViewModel> routes);

    public void showRouteDetail(RouteViewModel route);

    void showEmptyState();
}
