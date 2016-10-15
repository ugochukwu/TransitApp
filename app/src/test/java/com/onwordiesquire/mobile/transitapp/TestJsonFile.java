package com.onwordiesquire.mobile.transitapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Provider;
import com.onwordiesquire.mobile.transitapp.data.model.ProviderAttributes;
import com.onwordiesquire.mobile.transitapp.test.common.Utils;
import com.onwordiesquire.mobile.transitapp.util.ProviderAttributesTypeAdapter;
import com.onwordiesquire.mobile.transitapp.util.TransitAdapterFactory;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestJsonFile {
    private Gson gson;

    @Before
    public void setup() {
        gson = Utils.setupGson();
    }

    @Test
    public void parseJson_isCorrect() throws Exception {

        //act
        AvailableRoutes routesData = gson.fromJson(Utils.readJsonFile("data.json"),
                AvailableRoutes.class);

        String type = routesData.routes().get(0).type();

        //assert
        assertEquals("public_transport", type);
        assertNotEquals("public", type);


    }

    @Test
    public void TestProvidersCorrectlyParsed() throws Exception {
        //act
        AvailableRoutes routesData = gson.fromJson(Utils.readJsonFile("data.json"),
                AvailableRoutes.class);

        Provider vbb = routesData.providerData().providers().get(0);

        //assert
        assertEquals("vbb", vbb.providerName());

    }



}