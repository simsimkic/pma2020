package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupActivityAnswerDto implements Serializable
{
    private Long id;
    private Boolean accept;
    private String username;

    public GroupActivityAnswerDto(Long id, Boolean accept, String username) {
        this.id = id;
        this.accept = accept;
        this.username = username; //ko brise ili prihvata zahtev
    }

    public GroupActivityAnswerDto() {
    }
}
