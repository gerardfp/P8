package com.company.p8.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItunesApiModule {
    public static ItunesApi itunesApi = new Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItunesApi.class);
}

//public class ItunesApiModule {
//    public static ItunesApi itunesApi = new Retrofit.Builder()
//            .baseUrl("https://itunes.apple.com/")
//            .client(new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request();
//
//                            long t1 = System.nanoTime();
//                            Log.e("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
//                                    request.url(), chain.connection(), request.headers()));
//
//                            okhttp3.Response response = chain.proceed(request);
//
//                            long t2 = System.nanoTime();
//                            Log.e("INTERCEPTOR---", String.format("Received response for %s in %.1fms%n%s",
//                                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//                            return response;
//                        }
//                    })
//                    .build()
//            )
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ItunesApi.class);
//}

//class ItunesApiModuleSingleton {
//    static ItunesApi itunesApi;
//
//    public static ItunesApi getApi(){
//        if(itunesApi == null){
//            itunesApi = new Retrofit.Builder()
//                    .baseUrl("https://itunes.apple.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(ItunesApi.class);
//        }
//        return itunesApi;
//    }
//}
//
//class ItunesApiModuleInterceptor {
//    static ItunesApi itunesApi;
//
//    public static ItunesApi getApi(){
//        if(itunesApi == null){
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(new LoggingInterceptor())
//                    .addInterceptor(new ApiKeyInterceptor())
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .build();
//
//            itunesApi = new Retrofit.Builder()
//                    .baseUrl("https://itunes.apple.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(ItunesApi.class);
//        }
//        return itunesApi;
//    }
//}
//
//class ApiKeyInterceptor implements Interceptor {
//    @Override
//    public okhttp3.Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        HttpUrl originalHttpUrl = original.url();
//
//        HttpUrl url = originalHttpUrl.newBuilder()
//                .addQueryParameter("api_key", "YOUR_API_KEY")
//                .build();
//
//        Request.Builder requestBuilder = original.newBuilder()
//                .url(url);
//
//        Request request = requestBuilder.build();
//        return chain.proceed(request);
//    }
//}
