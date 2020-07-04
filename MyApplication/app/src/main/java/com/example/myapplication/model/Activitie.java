package com.example.myapplication.model;

import android.graphics.Bitmap;

import java.sql.Time;
import java.util.Date;

public class Activitie {
    private Long id;
    private String name;
    private Bitmap encodedMap;
    private Double distance;
    private Double duration;
    private String time;
    private String likes;
    private String comment;
    private String user;
    private String description;


    public Activitie(Long id, String name, Double distance, Double duration, String time, String likes,
                     String comment, String user, String description) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.time = time;
        this.likes = likes;
        this.comment = comment;
        this.user=user;
        this.description = description;
    }

    public Activitie(Long id, Double distance, Double duration, String time, Bitmap encodedMap, String description) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
        this.time = time;
        this.encodedMap = encodedMap;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getEncodedMap() {
        return encodedMap;
    }

    public void setEncodedMap(Bitmap encodedMap) {
        this.encodedMap = encodedMap;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
