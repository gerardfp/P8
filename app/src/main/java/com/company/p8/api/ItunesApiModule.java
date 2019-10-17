package com.company.p8.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItunesApiModule {
    static ItunesApi itunesApi;

    public static ItunesApi getApi(){
        if(itunesApi == null){
            itunesApi = new Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ItunesApi.class);
        }
        return itunesApi;
    }
}