package com.example.myapplication.dto.response;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class UserResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("dateOfBirth")
    private Date dateOfBirth;
    @SerializedName("address")
    private String address;
    @SerializedName("location")
    private String location;
    @SerializedName("biography")
    private String biography;
    @SerializedName("height")
    private double height;
    @SerializedName("weight")
    private double weight;
    @SerializedName("password")
    private String password;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String email, String username, Date dateOfBirth, String address, String location, String biography, double height, double weight, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.location = location;
        this.biography = biography;
        this.height = height;
        this.weight = weight;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
