package com.example.myapplication.dto.request;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class UserRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UserRequest(){

    }

    public UserRequest(String name, String username, String address, String email, String password) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
