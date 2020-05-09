package com.example.myapplication.mokap_data;

import com.example.myapplication.model.Activity;
import com.example.myapplication.model.State;

import java.util.ArrayList;

public class Activities {

    public static ArrayList<Activity> getActivities (){
        ArrayList<Activity> activities =  new ArrayList<>();
        activities.add(new Activity("Mika Mikic sent you an activity invitation.", State.RECEIVED, "15.04.2020", "check location"));
        activities.add(new Activity("Mika Mikic accepted your activity invitation.", State.ACCEPT, "15.04.2020", "check location"));
        activities.add(new Activity("Waiting for Djura Djuric to accept your activity invitation.", State.SENT, "15.04.2020", "check location"));

        return activities;
    }
}
