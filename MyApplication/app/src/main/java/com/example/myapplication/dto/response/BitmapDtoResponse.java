package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class BitmapDtoResponse {

    @SerializedName("encodedMap")
    private String encodedMap;
    @SerializedName("id")
    private Long id;
    @SerializedName("duration")
    private double duration;
    @SerializedName("distance")
    private double distance;
    @SerializedName("dateTime")
    private String dateTime;
    @SerializedName("steps")
    private int steps;
    @SerializedName("description")
    private String description;

    public BitmapDtoResponse() {
    }

    public BitmapDtoResponse(String encodedMap, Long id, double duration, double distance, String dateTime, int steps, String description) {
        this.encodedMap = encodedMap;
        this.id = id;
        this.duration = duration;
        this.distance = distance;
        this.dateTime = dateTime;
        this.steps = steps;
        this.description = description;
    }

    public String getEncodedMap() {
        return encodedMap;
    }

    public void setEncodedMap(String encodedMap) {
        this.encodedMap = encodedMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
