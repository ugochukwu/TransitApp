package com.onwordiesquire.mobile.transitapp.presentation.routedetails;

import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.TransitApp;
import com.onwordiesquire.mobile.transitapp.data.model.Route;
import com.onwordiesquire.mobile.transitapp.data.model.Segment;
import com.onwordiesquire.mobile.transitapp.data.model.Stop;
import com.onwordiesquire.mobile.transitapp.presentation.routeslist.SegmentsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.onwordiesquire.mobile.transitapp.presentation.routeslist.MainActivity.ROUTE_PAYLOAD;

public class RouteDetailsActivity extends AppCompatActivity implements RouteDetailsMvpView, OnMapReadyCallback {

    public static final String SEGMENT_PAYLOAD = RouteDetailsActivity.class.getSimpleName() + ".SEGMENT_PAYLOAD";
    private SparseArray<List<LatLng>> latLngs = new SparseArray<>();
    @Inject
    RouteDetailsPresenter rdPresenter;
    @BindView(R.id.segments_recycler)
    RecyclerView segmentRecycler;
    @BindView(R.id.timeline_recycler)
    RecyclerView timelineRecycler;
    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private Route route;
    private SegmentsAdapter adapter;
    private TimelineAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        TransitApp.appComponent().inject(this);
        ButterKnife.bind(this);
        setupRecycler();
        route = (Route) getIntent().getParcelableExtra(ROUTE_PAYLOAD);


        rdPresenter.attachView(this);
        rdPresenter.loadPolyines(route);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

    }

    private void setupRecycler() {
        segmentRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SegmentsAdapter();
        segmentRecycler.setAdapter(adapter);

        timelineRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        timelineAdapter = new TimelineAdapter();
        timelineRecycler.setAdapter(timelineAdapter);

    }


    @Override
    public void drawPolyLinesOnMap(List<String> polylines) {

        for (int position = 0; position < polylines.size(); position++) {

            String polyline = polylines.get(position);
            if (polyline != null) {
                Timber.i(polyline);
                latLngs.put(position, PolyUtil.decode(polyline));
            }
        }

        this.runOnUiThread(() -> {
            mapFragment.getMapAsync(this);
        });

        Timber.i("No of polylines is " + latLngs.size());
    }


    @Override
    public void showEmptyState() {
        Timber.i("Called empty state");
    }

    @Override
    public void drawSegmentsOnMap(List<Segment> segments) {
        //loop through segments and parse polyline into latlngs then create polyline on map
        for (Segment segment : segments) {

            String polyline = segment.polyline();
            if (polyline != null) {
                List<LatLng> points = PolyUtil.decode(polyline);
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.addAll(points);
                polylineOptions.color(Color.parseColor(segment.color()));
                polylineOptions.clickable(true);

                map.addPolyline(polylineOptions);

            }
        }


    }

    @Override
    public void centerMap(Stop first, Stop last) {

        LatLng start = new LatLng(first.lat(), first.lng());
        LatLng stop = new LatLng(last.lat(), last.lng());

        LatLngBounds.Builder builder = new LatLngBounds.Builder().include(start).include(stop);

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(builder.build().getCenter(), 13));

        MarkerOptions startMarkerOptions = new MarkerOptions();
        startMarkerOptions
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.man_pin))
                .position(start);
        MarkerOptions endMarkerOptions = new MarkerOptions();
        endMarkerOptions
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin))
                .alpha(1f)
                .position(stop);
        map.addMarker(startMarkerOptions);
        map.addMarker(endMarkerOptions);


    }

    @Override
    public void showSegmentsInView(List<Segment> segments) {
        adapter.setData(segments);
        adapter.notifyDataSetChanged();

        timelineAdapter.setData(segments);
        timelineAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        rdPresenter.loadBounds(route);
        rdPresenter.loadSegments(route);


    }
}
