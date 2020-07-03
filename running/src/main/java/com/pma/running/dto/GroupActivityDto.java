package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupActivityDto implements Serializable {
    private Long id;
    private String timestamp;
    private String username_requestor;
    private String username_requestee;
    private String location;

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
}
