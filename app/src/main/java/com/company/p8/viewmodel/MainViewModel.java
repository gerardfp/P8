package com.company.p8.viewmodel;

import android.app.Application;

import com.company.p8.api.ItunesApi;
import com.company.p8.api.ItunesApiModule;
import com.company.p8.model.Movie;
import com.company.p8.model.MoviesSearchResult;
import com.company.p8.model.SongsSearchResult;
import com.company.p8.model.Song;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    ItunesApi itunesApi;

    public MainViewModel(@NonNull Application application) {
        super(application);
        itunesApi = ItunesApiModule.getApi();
    }

    public LiveData<List<Song>> searchSongs(String term){
        final MutableLiveData<List<Song>> songList = new MutableLiveData<>();

        itunesApi.searchSongs(term).enqueue(new Callback<SongsSearchResult>() {
            @Override
            public void onResponse(Call<SongsSearchResult> call, Response<SongsSearchResult> response) {
                songList.postValue(response.body().results); // if != null
            }

            @Override
            public void onFailure(Call<SongsSearchResult> call, Throwable t) {}
        });

        return songList;
    }

    public LiveData<List<Movie>> searchMovies(String term){
        final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

        itunesApi.searchMovies(term).enqueue(new Callback<MoviesSearchResult>() {
            @Override
            public void onResponse(Call<MoviesSearchResult> call, Response<MoviesSearchResult> response) {
                movieList.postValue(response.body().results); // if != null
            }

            @Override
            public void onFailure(Call<MoviesSearchResult> call, Throwable t) {}
        });

        return movieList;
    }
}
