package com.pma.running.dto;

import lombok.Data;


import java.io.Serializable;

@Data
public class FriendshipRequestDto implements Serializable {
    private String requestor;
    private String requestee;
    private Boolean accept;

    public FriendshipRequestDto(){}
    public FriendshipRequestDto(String requestor, String requestee) {
        this.requestor = requestor;
        this.requestee = requestee;
    }

    public FriendshipRequestDto(String requestor, String requestee, Boolean accept) {
        this.requestor = requestor;
        this.requestee = requestee;
        this.accept = accept;
    }
}
