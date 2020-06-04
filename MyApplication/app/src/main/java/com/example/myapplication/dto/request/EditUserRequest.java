package com.example.myapplication.dto.request;

import com.example.myapplication.ui.EditProfileActivity;

import java.sql.Date;

public class EditUserRequest {

    private String name;
    private String username;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String biography;
    private String height;
    private String weight;

    public EditUserRequest(){

    }

    public EditUserRequest(String name, String username, String address, String email, Date dateOfBirth, String biography, String height, String weight) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.biography = biography;
        this.height = height;
        this.weight = weight;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
