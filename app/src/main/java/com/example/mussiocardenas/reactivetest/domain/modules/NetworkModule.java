package com.example.mussiocardenas.reactivetest.domain.modules;

import com.example.mussiocardenas.reactivetest.domain.Robot;
import com.example.mussiocardenas.reactivetest.domain.services.RobotService;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public OkHttpClient providesHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl("https://mussiocardenas.com")
                .build();
    }

    @Singleton
    @Provides
    public RobotService provideRobotService(Retrofit retrofit) {
        return retrofit.create(RobotService.class);
    }
}
