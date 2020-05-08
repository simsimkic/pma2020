package com.example.myapplication.model;

import java.io.Serializable;

public class Activity implements Serializable {

    private String description;
    private State state;
    private String date;
    private String location;

    public Activity(){}
    public Activity(String description, State state, String date, String location) {
        this.description = description;
        this.state  = state;
        this.date = date;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
