package com.company.p8.viewmodel;

import android.app.Application;

import com.company.p8.api.ItunesApiModule;
import com.company.p8.model.ItunesResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ItunesResponse> buscar(String term){
        final MutableLiveData<ItunesResponse> itunesResponse = new MutableLiveData<>();

        ItunesApiModule.itunesApi.buscar(term).enqueue(new Callback<ItunesResponse>() {
            @Override
            public void onResponse(Call<ItunesResponse> call, Response<ItunesResponse> response) {
                itunesResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ItunesResponse> call, Throwable t) {}
        });

        return itunesResponse;
    }
}
