package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ActivityRequestAnswer implements Serializable {
    private Long id;
    private Boolean accept;
    private String username;

    public ActivityRequestAnswer() {
    }

    public ActivityRequestAnswer(Long id, Boolean accept, String username) {
        this.id = id;
        this.accept = accept;
        this.username = username;
    }
}
