package com.example.myapplication.mokap_data;

import com.example.myapplication.model.Activitie;
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

    public static ArrayList<Activitie> getActivitie(){
        ArrayList<Activitie> activities = new ArrayList<>();
        activities.add(new Activitie(100L, "Evening run", 3.6, 45.0, "10.04.2020. 19:35", "3", "0", "Mika Mikic", "opis 1"));
        activities.add(new Activitie(101L, "Morning run", 3.6, 60.0, "10.04.2020. 07:35", "13", "2", "Pera Peric", "opis2"));
        return activities;

    }
}
