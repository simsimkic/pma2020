package com.example.myapplication.model;

public class Notification {
    public String description;
    public NotificationType type;
    public Activity activity;

    public Notification(String description, NotificationType type, Activity activity) {
        this.description = description;
        this.type = type;
        this.activity = activity;
    }

    public Notification(String description, NotificationType type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
