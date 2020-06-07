package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

public class BitmapDtoResponse {

    @SerializedName("encodedMap")
    private String encodedMap;

    public BitmapDtoResponse() {
    }

    public String getEncodedMap() {
        return encodedMap;
    }

    public void setEncodedMap(String encodedMap) {
        this.encodedMap = encodedMap;
    }
}
