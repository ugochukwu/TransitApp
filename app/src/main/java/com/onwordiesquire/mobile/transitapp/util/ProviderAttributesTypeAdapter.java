package com.onwordiesquire.mobile.transitapp.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.onwordiesquire.mobile.transitapp.data.model.Provider;
import com.onwordiesquire.mobile.transitapp.data.model.ProviderAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by michelonwordi on 10/15/16.
 */

public class ProviderAttributesTypeAdapter extends TypeAdapter<ProviderAttributes> {

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @param in
     * @return the converted Java object. May be null.
     */
    @Override
    public ProviderAttributes read(JsonReader in) throws IOException {
        List<Provider> providers = new ArrayList<>();

        JsonToken peek = in.peek();
        if (peek == JsonToken.BEGIN_OBJECT) {
            in.beginObject();

            while (in.hasNext()) {
                providers.add(processProviderObject(in));
            }
            in.endObject();

        } else {
            throw new IOException("Json is not correctly Formatted");
        }

        return ProviderAttributes.builder().setProviders(providers).build();
    }

    private Provider processProviderObject(JsonReader in) throws IOException {
        String providerName = "";

        //if its a name then its the provider name and should be stored for later use
        if (in.peek() == JsonToken.NAME) {
            providerName = in.nextName();
            Timber.i("Name of tag is " + providerName);
        }
        in.beginObject();
        Provider.Builder providerBuilder = Provider.builder().setProviderName(providerName);
        while (in.hasNext()) {
            JsonToken token = in.peek();
            if (token == JsonToken.NAME) {
                handleField(providerBuilder, in);
            }
        }
        in.endObject();
        return providerBuilder.build();
    }

    private void handleField(Provider.Builder providerBuilder, JsonReader in) throws IOException {
        switch (in.nextName()) {
            case "provider_icon_url":
                providerBuilder.setProviderIconUrl(in.nextString());
                break;
            case "disclaimer":
                providerBuilder.setDisclaimer(in.nextString());
                break;
            case "ios_itunes_url":
                providerBuilder.setiosItunesUrl(in.nextString());
                break;
            case "ios_app_url":
                providerBuilder.setiosAppUrl(in.nextString());
                break;
            case "android_package_name":
                providerBuilder.setAndroidPackageName(in.nextString());
                break;
            case "display_name":
                providerBuilder.setDisplayName(in.nextString());
                break;
            default:
                throw new IOException("Incorrectly formatted json");
        }
    }


    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param out
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, ProviderAttributes value) throws IOException {

    }
}
