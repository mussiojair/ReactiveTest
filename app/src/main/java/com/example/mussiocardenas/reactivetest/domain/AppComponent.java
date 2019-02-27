package com.example.mussiocardenas.reactivetest.domain;

import com.example.mussiocardenas.reactivetest.components.detail.DetailActivity;
import com.example.mussiocardenas.reactivetest.components.master.MasterActivity;
import com.example.mussiocardenas.reactivetest.domain.modules.AppModule;
import com.example.mussiocardenas.reactivetest.domain.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {
    void inject(MasterActivity activity);
    void inject(DetailActivity activity);
}
