package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentResponseDto implements Serializable {
    @SerializedName("user")
    private String user;
    @SerializedName("date")
    private String date;
    @SerializedName("comment")
    private String comment;
    @SerializedName("post_id")
    private Long post_id;

    public CommentResponseDto() {
    }

    public CommentResponseDto(String user, String date, String comment, Long post_id) {
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.post_id = post_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
