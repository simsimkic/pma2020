package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

public class FriendshipRequest {
    @SerializedName("requestor")
    private String requestor;
    @SerializedName("requestee")
    private String requestee;

    @SerializedName("accept")
    private Boolean accept;

    public FriendshipRequest(String requestor, String requestee) {
        this.requestor = requestor;
        this.requestee = requestee;
    }

    public FriendshipRequest(String requestor, String requestee, Boolean accept) {
        this.requestor = requestor;
        this.requestee = requestee;
        this.accept = accept;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRequestee() {
        return requestee;
    }

    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }
}
