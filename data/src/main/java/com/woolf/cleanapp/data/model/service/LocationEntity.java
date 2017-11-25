package com.woolf.cleanapp.data.model.service;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationEntity {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("position")
    @Expose
    private PositionEntity position;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }
}
