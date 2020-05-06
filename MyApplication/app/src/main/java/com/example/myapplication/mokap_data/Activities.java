package com.example.myapplication.mokap_data;

import com.example.myapplication.model.Activity;

import java.util.ArrayList;

public class Activities {

    public static ArrayList<Activity> getActivities (){
        ArrayList<Activity> activities =  new ArrayList<>();
        activities.add(new Activity("Mika Mikic sent you an activity invitation.", false, "15.04.2020", "check location"));
        activities.add(new Activity("Mika Mikic accepted your activity invitation.", false, "15.04.2020", "check location"));
        activities.add(new Activity("Waiting for Djura Djuric to accept your activity invitation.", false, "15.04.2020", "check location"));

        return activities;
    }
}
