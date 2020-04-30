package com.example.myapplication.model;

import java.sql.Time;
import java.util.Date;

public class Activitie {
    private String name;
    private Double distance;
    private Double duration;
    private String time;

    public Activitie(String name, Double distance, Double duration, String time) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
