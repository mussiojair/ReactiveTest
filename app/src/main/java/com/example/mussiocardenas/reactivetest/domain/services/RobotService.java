package com.example.mussiocardenas.reactivetest.domain.services;

import com.example.mussiocardenas.reactivetest.domain.Robot;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RobotService {

    @GET("/robots.json")
    Observable<List<Robot>> getRobots();
}
