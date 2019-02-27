package com.example.mussiocardenas.reactivetest.domain.repositories;

import com.example.mussiocardenas.reactivetest.domain.Robot;
import com.example.mussiocardenas.reactivetest.domain.services.RobotService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RobotsRepository {

    private RobotService robotService;

    public RobotsRepository(RobotService robotService){
        this.robotService = robotService;
    }

    public Observable<List<Robot>> getRobots(){
        return robotService.getRobots();
    }
}
