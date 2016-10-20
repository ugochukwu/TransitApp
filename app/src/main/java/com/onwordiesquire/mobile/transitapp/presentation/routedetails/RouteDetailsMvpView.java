package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.presentation.MvpView;

import java.util.List;

/**
 * Created by michelonwordi on 10/18/16.
 */

public interface RouteDetailsMvpView extends MvpView {

    public void drawPolyLinesOnMap(List<String> polyline);

    void showEmptyState();

    void drawSegmentsOnMap(List<Segment> segments);

    void centerMap(Stop first,Stop last);

    void showSegmentsInBottomSheet(List<Segment> segments);
}
