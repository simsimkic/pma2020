package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class PostResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("description")
    private String description;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("like_num")
    private int like_num;
    @SerializedName("comment_num")
    private int comment_num;
    @SerializedName("date")
    private String date;
    @SerializedName("bitmap")
    private String bitmap;
    @SerializedName("user")
    private String user;


    @SerializedName("distance")
    private double distance;

    @SerializedName("duration")
    private double duration;

    @SerializedName("like")
    private boolean like;

    @SerializedName("comments")
    private List<CommentResponseDto> comments;

    public PostResponse() { }

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

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public List<CommentResponseDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDto> comments) {
        this.comments = comments;
    }
}
