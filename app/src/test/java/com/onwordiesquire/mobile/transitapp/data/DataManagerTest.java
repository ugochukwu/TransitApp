package com.onwordiesquire.mobile.transitapp.data;

import com.google.gson.Gson;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.source.JsonRoutesDataSource;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


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

    @Before
    public void setup()
    {
        dataManager = new DataManager(mockJsonRoutesDataSource);
    }

    @Test
    public void getAvailableRoutes() throws Exception {

        //arrange
        //load data from disk for use in testing the datamanager
        Gson gson = Utils.setupGson();
        String jsonFile = Utils.readJsonFile("data.json");
        AvailableRoutes availableRoutes = gson.fromJson(jsonFile, AvailableRoutes.class);

        //using mockito provide expected result while avoiding any use of the android application context as required by jsonRoutesDataSource
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        TestSubscriber<AvailableRoutes> testSubscriber = new TestSubscriber<>();

        //act
        dataManager.getAvailableRoutes().subscribe(testSubscriber);

        //assert
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(availableRoutes);

    }

}