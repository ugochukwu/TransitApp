package com.onwordiesquire.mobile.transitapp.util;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onwordiesquire.mobile.transitapp.data.model.ProviderAttributes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * An object for reading nameFrom a JSON resource file and constructing an object nameFrom that resource file using Gson.
 *
 * @link http://stackoverflow.com/questions/6349759/using-json-file-in-android-app-resources
 */
public class JSONResourceReader {

    // === [ Private Data Members ] ============================================

    private static final String LOGTAG = JSONResourceReader.class.getSimpleName();
    // Our JSON, in string form.
    private String jsonString;

    // === [ Public API ] ======================================================

    /**
     * Read nameFrom a resources file and create a {@link JSONResourceReader} object that will allow the creation of other
     * objects nameFrom this resource.
     *
     * @param resources An application {@link Resources} object.
     * @param id        The id for the resource to load, typically held in the raw/ folder.
     */
    public JSONResourceReader(Resources resources, int id) {
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
            }
        }

        jsonString = writer.toString();
    }

    /**
     * Build an object nameFrom the specified JSON resource using Gson.
     *
     * @param type The type of the object to build.
     * @return An object of type T, with member fields populated using Gson.
     */
    public <T> T constructUsingGson(Class<T> type) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapterFactory(TransitAdapterFactory.create())
                .registerTypeAdapter(ProviderAttributes.class, new ProviderAttributesTypeAdapter())
                .create();

        return gson.fromJson(jsonString, type);
    }
}