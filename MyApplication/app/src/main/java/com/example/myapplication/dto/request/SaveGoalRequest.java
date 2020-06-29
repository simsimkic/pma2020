package com.example.myapplication.dto.request;

public class SaveGoalRequest {

    private String title;
    private double duration;
    private double distance;
    private String timestampe;
    private String end_time;
    private Boolean archived;
    private Long user_id;
    private Long id;

    public SaveGoalRequest(String title, double duration, double distance, String timestampe, String end_time, Long user_id, Long id, boolean archived) {
        this.title = title;
        this.duration = duration;
        this.distance = distance;
        this.timestampe = timestampe;
        this.end_time = end_time;
        this.user_id = user_id;
        this.id = id;
        this.archived = archived;
    }

    public SaveGoalRequest() {
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public String getTimestampe() {
        return timestampe;
    }

    public String getEnd_time() {
        return end_time;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTimestampe(String timestampe) {
        this.timestampe = timestampe;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
