package com.company.p8.api;

import com.company.p8.model.ItunesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesApi {
    @GET("search/")
    Call<ItunesResponse> buscar(@Query("term") String term);
}
