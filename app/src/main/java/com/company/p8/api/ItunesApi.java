package com.company.p8.api;

import com.company.p8.model.MoviesSearchResult;
import com.company.p8.model.SongsSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesApi {
    @GET("search/")
    Call<SongsSearchResult> searchSongs(@Query("term") String term);

    @GET("search/")
    Call<MoviesSearchResult> searchMovies(@Query("term") String term);

}
