package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstace {
    private static Retrofit retrofit;
    private static final String BASE_URL= "https://api.exchangeratesapi.io/";

    public static Retrofit getRetrofitInstance(){
        System.out.println(BASE_URL);
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }
    
}
