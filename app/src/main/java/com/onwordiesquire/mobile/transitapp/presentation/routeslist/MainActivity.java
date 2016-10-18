package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.TransitApp;
import com.onwordiesquire.mobile.transitapp.data.DataManager;
import com.onwordiesquire.mobile.transitapp.data.model.Route;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RoutesListMvpView {

    @Inject
    RoutesListPresenter routesListPresenter;

    @BindView(R.id.routes_list)
    RecyclerView routesRecycler;
    private RoutesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransitApp.appComponent().inject(this);
        ButterKnife.bind(this);
        routesListPresenter.attachView(this);

        setupRecycler();
        routesListPresenter.loadAvailableRoutes();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        routesListPresenter.detachView();
    }

    private void setupRecycler() {
        routesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new RoutesListAdapter();
        routesRecycler.setAdapter(adapter);
    }

    @Override
    public void showRoutes(List<RouteViewModel> routes) {
        adapter.setData(routes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRouteDetail(Route route) {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showEmptyState() {

    }
}
