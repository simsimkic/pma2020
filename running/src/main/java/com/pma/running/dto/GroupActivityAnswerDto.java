package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupActivityAnswerDto implements Serializable
{
    private Long id;
    private Boolean accept;

    public GroupActivityAnswerDto(Long id, Boolean accept) {
        this.id = id;
        this.accept = accept;
    }

    public GroupActivityAnswerDto() {
    }
}
