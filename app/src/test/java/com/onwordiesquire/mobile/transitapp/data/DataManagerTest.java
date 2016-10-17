package com.onwordiesquire.mobile.transitapp.data;

import com.google.gson.Gson;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.data.source.JsonRoutesDataSource;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;
import com.onwordiesquire.mobile.transitapp.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.io.IOException;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {


    DataManager dataManager;
    @Mock
    JsonRoutesDataSource mockJsonRoutesDataSource;
    private AvailableRoutes availableRoutes;



    @Before
    public void setup() throws IOException {
        dataManager = new DataManager(mockJsonRoutesDataSource);
        //load data from disk for use in testing the datamanager
        Gson gson = Utils.setupGson();
        String jsonFile = Utils.readJsonFile("data.json");
        availableRoutes = gson.fromJson(jsonFile, AvailableRoutes.class);
    }

    @Test
    public void getAvailableRoutes() throws Exception {


        //arrange
        //using mockito provide expected result while avoiding any use of the android application context as required by jsonRoutesDataSource
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        TestSubscriber<AvailableRoutes> testSubscriber = new TestSubscriber<>();

        //act
        dataManager.loadRoutesData().subscribe(testSubscriber);

        //assert
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(availableRoutes);

    }


    @Test
    public void testGetStartingPoints() throws Exception {
        //arrange
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        TestSubscriber<Stop> testSubscriber = new TestSubscriber<>();
        //act
        dataManager.getStartPoints().distinct().subscribe(testSubscriber);

        //assert
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(4);
//        testSubscriber.assertValues(Stop.builder().setName("Leipziger Platz 7, 10117 Berlin, Deutschland").setLat(52.509071)
//                .setLng(13.377977)
//                .setDateTime("2015-04-17T12:43:00+01:00")
//                .build());


    }

    @Test
    public void testGetEndPoints() throws Exception {
        //arrange
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        TestSubscriber<Stop> testSubscriber = new TestSubscriber<>();
        //act
        dataManager.getEndPoints().distinct().subscribe(testSubscriber);

        //assert
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(8);
//        testSubscriber.getOnNextEvents().contains(Stop.builder().setName("Leipziger Platz 7, 10117 Berlin, Deutschland").setLat(52.509071)
//                .setLng(13.377977)
//                .setDateTime("2015-04-17T12:43:00+01:00")
//                .build());
    }

    @Test
    public void testGetRoutesForPoints() {
        //arrange
        Stop leipziger = Stop.builder().setName("Leipziger Platz 7, 10117 Berlin, Deutschland").setLat(52.509071)
                .setLng(13.377977)
                .setDateTime("2015-04-17T12:43:00+01:00")
                .build();
        Stop torstrasse = Stop.builder().setName("Torstraße 103, 10119 Berlin, Deutschland")
                .setLat(52.5302356)
                .setLng(13.4033659)
                .setDateTime("2015-04-17T12:29:00+01:00")
                .setProperties(null)
                .build();
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        TestSubscriber<Route> testSubscriber = new TestSubscriber<>();
        //act
        dataManager.getAvailableRoutesForPoints(torstrasse,leipziger).subscribe(testSubscriber);

    }


}