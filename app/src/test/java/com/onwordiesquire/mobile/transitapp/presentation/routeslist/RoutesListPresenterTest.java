package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import com.google.gson.Gson;
import com.onwordiesquire.mobile.transitapp.data.DataManager;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.source.JsonRoutesDataSource;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;
import com.onwordiesquire.mobile.transitapp.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/17/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoutesListPresenterTest {

    RoutesListPresenter routesListPresenter;
    DataManager dataManager;

    @Captor
    ArgumentCaptor<List<RouteViewModel>> routesArgCaptor;

    @Mock
    JsonRoutesDataSource mockJsonRoutesDataSource;
    @Mock
    RoutesListMvpView mockRoutesListView;
    private AvailableRoutes availableRoutes;

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() throws IOException {
        dataManager = new DataManager(mockJsonRoutesDataSource);
        routesListPresenter = new RoutesListPresenter(dataManager);

        routesListPresenter.attachView(mockRoutesListView);

        //load data from disk for use in testing the datamanager
        Gson gson = Utils.setupGson();
        String jsonFile = Utils.readJsonFile("data.json");
        availableRoutes = gson.fromJson(jsonFile, AvailableRoutes.class);
    }

    @Test
    public void loadAvailableRoutes() throws Exception {
        //arrange
        when(mockJsonRoutesDataSource.getAvailableRoutes()).thenReturn(Observable.just(availableRoutes));
        List<Route> routes =availableRoutes.routes();
        //act
        routesListPresenter.loadAvailableRoutes();

        //assert
        verify(mockRoutesListView).showRoutes(routesArgCaptor.capture());
        assertEquals(routes.size(),routesArgCaptor.getValue().size());
    }

}