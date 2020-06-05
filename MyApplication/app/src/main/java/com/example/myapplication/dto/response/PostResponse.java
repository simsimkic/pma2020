package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("description")
    private String description;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("like_num")
    private int like_num;

    public PostResponse() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
