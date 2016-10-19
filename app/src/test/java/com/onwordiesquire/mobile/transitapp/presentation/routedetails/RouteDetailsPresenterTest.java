package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import com.google.android.gms.maps.model.Polyline;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;
import com.onwordiesquire.mobile.transitapp.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by michelonwordi on 10/19/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RouteDetailsPresenterTest {

    RouteDetailsPresenter presenter;
    @Mock
    RouteDetailsMvpView mockRDView;

    ArgumentCaptor<List<String>> encodedPolylines = new ArgumentCaptor<>();
    ArgumentCaptor<List<Segment>> segments = new ArgumentCaptor<>();

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();
    private List<Route> routes;

    @Before
    public void setup() throws IOException {
        presenter = new RouteDetailsPresenter();
        presenter.attachView(mockRDView);

        routes = Utils.getMockRoutes().routes();


    }

    @Test
    public void loadPolyines() throws Exception {

        //arrange
        Route route = routes.get(0);
        int noOfSegments = route.segments().size();

        //act
        presenter.loadPolyines(route);

        //assert
        verify(mockRDView).drawPolyLinesOnMap(encodedPolylines.capture());
        assertTrue(encodedPolylines.getValue()!= null);
        assertEquals(noOfSegments,encodedPolylines.getValue().size());
    }

    @Test
    public void loadSegments() throws Exception{
        //arrange
        Route route = routes.get(0);
        int noOfSegments = route.segments().size();

        //act
        presenter.loadSegments(route);

        //assert
        verify(mockRDView).drawSegmentsOnMap(segments.capture());
        assertTrue(segments.getValue() != null);
        assertEquals(noOfSegments,segments.getValue().size());
    }

}