package com.example.mussiocardenas.reactivetest;

import android.support.multidex.MultiDexApplication;

import com.example.mussiocardenas.reactivetest.domain.AppComponent;
import com.example.mussiocardenas.reactivetest.domain.DaggerAppComponent;
import com.example.mussiocardenas.reactivetest.domain.modules.AppModule;
import com.example.mussiocardenas.reactivetest.domain.modules.NetworkModule;

public class ReactiveTestApp extends MultiDexApplication {

    private static ReactiveTestApp application;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setAppComponent(DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModule()).build());
    }

    public static ReactiveTestApp getApplication() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
