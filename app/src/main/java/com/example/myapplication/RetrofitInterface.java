package com.example.myapplication;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {


    @GET("latest")
    Call<data_model> getAllData();

}
