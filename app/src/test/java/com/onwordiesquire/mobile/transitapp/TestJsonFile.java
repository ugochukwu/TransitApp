package com.onwordiesquire.mobile.transitapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onwordiesquire.mobile.transitapp.data.model.AvailableRoutes;
import com.onwordiesquire.mobile.transitapp.data.model.Provider;
import com.onwordiesquire.mobile.transitapp.data.model.ProviderAttributes;
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
    private static final String ASSET_BASE_PATH = "../app/src/test/resources/";
    private Gson gson;

    @Before
    public void setup()
    {
        //arrange
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(TransitAdapterFactory.create())
                .registerTypeAdapter(ProviderAttributes.class, new ProviderAttributesTypeAdapter())
                .create();
    }

    @Test
    public void parseJson_isCorrect() throws Exception {

        //act
        AvailableRoutes routesData = gson.fromJson(readJsonFile("data.json"),
                AvailableRoutes.class);

        String type = routesData.routes().get(0).type();

        //assert
        assertEquals("public_transport", type);
        assertNotEquals("public", type);


    }

    @Test
    public void TestProvidersCorrectlyParsed() throws Exception{
        //act
        AvailableRoutes routesData = gson.fromJson(readJsonFile("data.json"),
                AvailableRoutes.class);

        Provider vbb = routesData.providerData().providers().get(0);

        //assert
        assertEquals("vbb",vbb.providerName());

    }


    private String readJsonFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ASSET_BASE_PATH + filename)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        return sb.toString();
    }
}