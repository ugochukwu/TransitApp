package com.onwordiesquire.mobile.transitapp.presentation.routeslist;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.TransitApp;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.presentation.routedetails.RouteDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RoutesListMvpView, RoutesListAdapter.OnRouteSelectedListener {

    public static final String ROUTE_PAYLOAD = MainActivity.class.getSimpleName() + ".ROUTE_PAYLOAD";
    @Inject
    RoutesListPresenter routesListPresenter;

    @BindView(R.id.routes_list)
    RecyclerView routesRecycler;
    View route_item_layout;
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
        adapter.setListener(this);
        routesRecycler.setAdapter(adapter);
    }

    @Override
    public void showRoutes(List<RouteViewModel> routes) {
        adapter.setData(routes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRouteDetail(RouteViewModel route) {
        Intent intent = new Intent(this, RouteDetailsActivity.class);
        intent.putExtra(ROUTE_PAYLOAD, route);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, route_item_layout, getResources().getString(R.string.route_trans));
        startActivity(intent, options.toBundle());
//        startActivity(intent);
    }



    @Override
    public void showEmptyState() {
// TODO: 10/20/16 implement empty state view in future
    }

    @Override
    public void onRouteSelected(RouteViewModel route,View view) {
        route_item_layout = view;
        routesListPresenter.openRouteDetails(route);
    }
}
