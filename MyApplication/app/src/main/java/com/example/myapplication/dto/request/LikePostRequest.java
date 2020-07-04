package com.example.myapplication.dto.request;

import java.io.Serializable;


public class LikePostRequest implements Serializable {
    private Long post_id;
    private String username;

    public LikePostRequest() {
    }

    public LikePostRequest(Long post_id, String username) {
        this.post_id = post_id;
        this.username = username;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
