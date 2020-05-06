package com.example.myapplication.model;

public class Activity {

    private String description;
    private boolean accept;
    private String date;
    private String location;

    public Activity(){}
    public Activity(String description, boolean accept, String date, String location) {
        this.description = description;
        this.accept = accept;
        this.date = date;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
