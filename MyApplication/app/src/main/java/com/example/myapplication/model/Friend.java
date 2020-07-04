package com.example.myapplication.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Friend implements Serializable {
    private String name;
    private boolean friends;

    public Friend(String name) {
        this.name = name;
    }

    public Friend(String name, boolean friends) {
        this.name = name;
        this.friends = friends;
    }

    public boolean isFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
