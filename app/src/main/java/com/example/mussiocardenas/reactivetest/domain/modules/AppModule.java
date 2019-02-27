package com.example.mussiocardenas.reactivetest.domain.modules;

import com.example.mussiocardenas.reactivetest.ReactiveTestApp;
import com.example.mussiocardenas.reactivetest.domain.repositories.RobotsRepository;
import com.example.mussiocardenas.reactivetest.domain.services.RobotService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private ReactiveTestApp reactiveTestApp;

    public AppModule(ReactiveTestApp app){
        reactiveTestApp = app;
    }

    @Singleton
    @Provides
    public RobotsRepository robotsRepository(RobotService robotService){
        return new RobotsRepository(robotService);
    }
}
