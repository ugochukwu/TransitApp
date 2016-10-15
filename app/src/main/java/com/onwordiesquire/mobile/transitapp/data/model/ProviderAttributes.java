package com.onwordiesquire.mobile.transitapp.data.model;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class ProviderAttributes {

    public abstract List<Provider> providers();

    public static Builder builder() {
        return new AutoValue_ProviderAttributes.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setProviders(List<Provider> providers);

        public abstract ProviderAttributes build();
    }

}
