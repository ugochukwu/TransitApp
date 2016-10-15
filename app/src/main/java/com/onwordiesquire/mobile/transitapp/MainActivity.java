package com.onwordiesquire.mobile.transitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.onwordiesquire.mobile.transitapp.data.DataManager;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransitApp.appComponent().inject(this);


    }
}
