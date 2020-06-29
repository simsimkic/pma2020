package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoalResponse implements Serializable {

    @SerializedName("id")
    private Long id;
    @SerializedName("duration")
    private double duration;
    @SerializedName("distance")
    private double distance;
    @SerializedName("end_time")
    private String end_time;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("title")
    private String title;
    @SerializedName("archived")
    private Boolean archived;

    public GoalResponse() {
    }

    public GoalResponse(Long id, double duration, double distance, String end_time, String timestamp, String title, Boolean archived) {
        this.id = id;
        this.duration = duration;
        this.distance = distance;
        this.end_time = end_time;
        this.timestamp = timestamp;
        this.title = title;
        this.archived = archived;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
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

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
