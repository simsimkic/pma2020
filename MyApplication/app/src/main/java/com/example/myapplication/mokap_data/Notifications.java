package com.example.myapplication.mokap_data;

import com.example.myapplication.model.Activity;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.NotificationType;
import com.example.myapplication.model.State;

import java.util.ArrayList;

public class Notifications {

    public static ArrayList<Notification> getNotifications(){
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Mika Mikic comment  your post", NotificationType.COMMENT));
        notifications.add(new Notification("Mika Mikic liked your post", NotificationType.LIKE));
        notifications.add(new Notification("Pera Peric sent you a friend request", NotificationType.SEND_FRIEND));
        notifications.add(new Notification("", NotificationType.SEND_INVITATION, new Activity("Mika Mikic sent you an activity invitation.", State.RECEIVED, "15.04.2020", "check location")));
        notifications.add(new Notification("Mika Mikic accepted your activity invitation", NotificationType.ACCEPT_INVITATION));
        notifications.add(new Notification("Mika Mikic accepted your friend request", NotificationType.ACCEPT_FRIEND));
        return notifications;

    }
}
