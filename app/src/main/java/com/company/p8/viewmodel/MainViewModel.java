package com.company.p8.viewmodel;

import android.app.Application;

import com.company.p8.api.ItunesApiModule;
import com.company.p8.model.ItunesResponse;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<String> searchTerm = new MutableLiveData<>();

    public LiveData<ItunesResponse> itunesResponse = Transformations.switchMap(searchTerm, new Function<String, LiveData<ItunesResponse>>() {
        @Override
        public LiveData<ItunesResponse> apply(String term) {
            final MutableLiveData<ItunesResponse> itunesResponseMutableLiveData = new MutableLiveData<>();

            ItunesApiModule.itunesApi.buscar(term).enqueue(new Callback<ItunesResponse>() {
                @Override
                public void onResponse(Call<ItunesResponse> call, Response<ItunesResponse> response) {
                    itunesResponseMutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<ItunesResponse> call, Throwable t) {}
            });

            return itunesResponseMutableLiveData;
        }
    });

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTerm(String term){
        searchTerm.setValue(term);
    }
}
