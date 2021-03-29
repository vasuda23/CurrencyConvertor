package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterfaceChart {


    @GET("")
    Call<chart_model> getChartData();


}
