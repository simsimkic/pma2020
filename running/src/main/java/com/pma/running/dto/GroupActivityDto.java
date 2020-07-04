package com.pma.running.dto;

import com.pma.running.model.ActivityRequest;
import com.pma.running.model.ActivityRequestStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Data
public class GroupActivityDto implements Serializable {
    private Long id;
    private String timestamp;
    private String username_requestor;
    private String username_requestee;
    private String location;
    private ActivityRequestStatus status;

    public GroupActivityDto() {
    }

    public GroupActivityDto(Long id, String timestamp, String username_requestor, String username_requestee, String location) {
        this.id = id;
        this.timestamp = timestamp;
        this.username_requestor = username_requestor;
        this.username_requestee = username_requestee;
        this.location = location;
    }

    public GroupActivityDto(String timestamp, String username_requestor, String username_requestee, String location) {
        this.timestamp = timestamp;
        this.username_requestor = username_requestor;
        this.username_requestee = username_requestee;
        this.location = location;
    }

    public GroupActivityDto(Long id, String timestamp, String username_requestor, String username_requestee, String location, ActivityRequestStatus status) {
        this.id = id;
        this.timestamp = timestamp;
        this.username_requestor = username_requestor;
        this.username_requestee = username_requestee;
        this.location = location;
        this.status = status;
    }

    public GroupActivityDto(ActivityRequest ar){
        this.id = ar.getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.timestamp = formatter.format(ar.getTimestamp());
        this.username_requestee = ar.getActivityRequestee().getName();
        this.username_requestor = ar.getActivityRequestor().getName();
        this.location = ar.getLocation();
        this.status = ar.getStatus();
    }
}
