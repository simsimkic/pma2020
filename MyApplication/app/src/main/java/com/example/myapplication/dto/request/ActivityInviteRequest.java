package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ActivityInviteRequest implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("username_requestor")
    private String username_requestor;
    @SerializedName("username_requestee")
    private String username_requestee;
    @SerializedName("location")
    private String location;
    @SerializedName("status")
    private ActivityRequestStatus status;


    public ActivityInviteRequest() {
    }

    public ActivityInviteRequest(String timestamp, String username_requestor, String username_requestee, String location) {
        this.timestamp = timestamp;
        this.username_requestor = username_requestor;
        this.username_requestee = username_requestee;
        this.location = location;
    }

    public ActivityInviteRequest(Long id, String timestamp, String username_requestor, String username_requestee, String location, ActivityRequestStatus status) {
        this.id = id;
        this.timestamp = timestamp;
        this.username_requestor = username_requestor;
        this.username_requestee = username_requestee;
        this.location = location;
        this.status = status;
    }

    public ActivityRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityRequestStatus status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername_requestor() {
        return username_requestor;
    }

    public void setUsername_requestor(String username_requestor) {
        this.username_requestor = username_requestor;
    }

    public String getUsername_requestee() {
        return username_requestee;
    }

    public void setUsername_requestee(String username_requestee) {
        this.username_requestee = username_requestee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
