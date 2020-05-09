package com.example.myapplication.mokap_data;

import com.example.myapplication.model.Friend;

import java.util.ArrayList;

public class Friends {

    public static ArrayList<Friend> getFriends(){
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Mika Mikic", true));
        friends.add(new Friend("Petar Petrovic", false));
        return friends;
    }
}
