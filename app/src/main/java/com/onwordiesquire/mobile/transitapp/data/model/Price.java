package com.onwordiesquire.mobile.transitapp.data.model;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michelonwordi on 10/15/16.
 */
@AutoValue
public abstract class Price {

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("amount")
    public abstract String amount();

    public static Builder builder()
    {
        return new AutoValue_Price.Builder();
    }

    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder setCurrency(String currency);

        abstract Builder setAmount(String amount);

        abstract Price build();
    }

    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Price> typeAdapter(Gson gson)
    {
        return new AutoValue_Price.GsonTypeAdapter(gson);
    }
}
