package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;


public class ActivityDto {

    @SerializedName("description")
    private String description;
    @SerializedName("duration")
    private double duration;
    @SerializedName("distance")
    private double distance;
    @SerializedName("steps")
    private int steps;
    @SerializedName("dateTime")
    private String dateTime;
    @SerializedName("username")
    private String username;
    @SerializedName("encodedMap")
    private String encodedMap;

    public ActivityDto() { }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEncodedMap(String encodedMap) {
        this.encodedMap = encodedMap;
    }
}
