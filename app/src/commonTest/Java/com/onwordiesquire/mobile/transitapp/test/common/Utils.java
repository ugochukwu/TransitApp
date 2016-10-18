package com.onwordiesquire.mobile.transitapp.test.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.ProviderAttributes;
import com.onwordiesquire.mobile.transitapp.util.ProviderAttributesTypeAdapter;
import com.onwordiesquire.mobile.transitapp.util.TransitAdapterFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michelonwordi on 10/15/16.
 */

public class Utils {

    private static final String ASSET_BASE_PATH = "../app/src/test/resources/";


    public static String readJsonFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ASSET_BASE_PATH + filename)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        return sb.toString();
    }


    public static Gson setupGson() {
        return  new GsonBuilder()
                .registerTypeAdapterFactory(TransitAdapterFactory.create())
                .registerTypeAdapter(ProviderAttributes.class, new ProviderAttributesTypeAdapter())
                .create();
    }

    public static AvailableRoutes getMockRoutes() throws IOException {
       return setupGson().fromJson(Utils.readJsonFile("data.json"),
                AvailableRoutes.class);
    }
}
