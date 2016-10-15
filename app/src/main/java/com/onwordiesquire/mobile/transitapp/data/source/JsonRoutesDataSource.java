package com.onwordiesquire.mobile.transitapp.data.source;

import android.content.Context;

import com.onwordiesquire.mobile.transitapp.R;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.util.JSONResourceReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by michelonwordi on 10/15/16.
 */
@Singleton
public class JsonRoutesDataSource {

    private final Context context;

    @Inject
    public JsonRoutesDataSource(Context context) {
        this.context = context;
    }


    public Observable<AvailableRoutes> getAvailableRoutes() {
        JSONResourceReader jsonResourceReader = new JSONResourceReader(context.getResources(), R.raw.data);
        AvailableRoutes availableRoutes = jsonResourceReader.constructUsingGson(AvailableRoutes.class);

        return Observable.create(subscriber -> {
            subscriber.onNext(availableRoutes);
            subscriber.onCompleted();
        });
    }

}
