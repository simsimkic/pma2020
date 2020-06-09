package com.pma.running.dto;

import org.apache.tomcat.jni.Local;

import java.io.Serializable;


public class PostDto implements Serializable {
    private Long id;
    private String description;
    private int visibility;
    private int like_num;
    private int comment_num;
    private String date;
    private String bitmap;
    private String user;
    private double distance;
    private double duration;

    public PostDto() {
    }

    public PostDto(Long id, String description, int visibility, int like_num, int comment_num, String date, String bitmap, String user, double distance, double duration) {
        this.id = id;
        this.description = description;
        this.visibility = visibility;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.date = date;
        this.bitmap = bitmap;
        this.user = user;
        this.distance = distance;
        this.duration = duration;
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

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
