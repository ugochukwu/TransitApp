package com.onwordiesquire.mobile.transitapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michelonwordi on 10/14/16.
 */
@AutoValue
public abstract class Provider {

    public abstract String providerName();

    @SerializedName("display_name")
    @Nullable
    public abstract String displayName();

    @SerializedName("ios_app_url")
    @Nullable
    public abstract String iosAppUrl();

    @SerializedName("ios_itunes_url")
    @Nullable
    public abstract String iosItunesUrl();

    @SerializedName("disclaimer")
    @Nullable
    public abstract String disclaimer();

    @SerializedName("provider_icon_url")
    @Nullable
    public abstract String providerIconUrl();

    @SerializedName("android_package_name")
    @Nullable
    public abstract String androidPackageName();

    public static Builder builder() {
        return new AutoValue_Provider.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setProviderName(String providerName);

        public abstract Builder setDisplayName(String displayName);

        public abstract Builder setiosItunesUrl(String iOSItunesUrl);

        public abstract Builder setDisclaimer(String disclaimer);

        public abstract Builder setProviderIconUrl(String providerIconUrl);

        public abstract Builder setAndroidPackageName(String androidPackageName);

        public abstract Builder setiosAppUrl(String iOSAppUrl);

        public abstract Provider build();


    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Provider> typeAdapter(Gson gson) {
        return new AutoValue_Provider.GsonTypeAdapter(gson);
    }
}

