package com.onwordiesquire.mobile.transitapp.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/20/16.
 */
@AutoValue
public abstract class Properties implements Parcelable {

    @Nullable
    @SerializedName("companies")
    public abstract List<Company> companies();

    @Nullable
    public abstract String address();

    @Nullable
    public abstract String model();

    @Nullable
    @SerializedName("license_plate")
    public abstract String licensePlate();

    @SerializedName("fuel_level")
    @Nullable
    public abstract String fuelLevel();

    @SerializedName("engine_type")
    @Nullable
    public abstract String engineType();

    @SerializedName("internal_cleanliness")
    @Nullable
    public abstract String internalCleanliness();

    @Nullable
    public abstract String seats();

    @Nullable
    public abstract String doors();

    @Nullable
    public abstract String description();

    public static Builder builder()

    {
        return new AutoValue_Properties.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        abstract Builder setCompanies(List<Company> companies);

        abstract Builder setSeats(String seats);

        abstract Builder setAddress(String address);

        abstract Builder setFuelLevel(String fuelLevel);

        abstract Builder setInternalCleanliness(String seats);

        abstract Builder setEngineType(String engineType);

        abstract Builder setDoors(String doors);
        abstract Builder setModel(String model);
        abstract Builder setLicensePlate(String licencePlate);

        abstract Builder setDescription(String description);

        abstract Properties build();

    }


    //enables the automatic generation of a Gson type adapter for this class
    public static TypeAdapter<Provider> typeAdapter(Gson gson) {
        return new AutoValue_Provider.GsonTypeAdapter(gson);
    }
}
