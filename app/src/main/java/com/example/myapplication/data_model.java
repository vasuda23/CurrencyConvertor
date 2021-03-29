package com.example.myapplication;

import android.app.MediaRouteActionProvider;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.util.Map;


public class data_model {

    @SerializedName("rates")
    @Expose
    Rates rates;

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}



