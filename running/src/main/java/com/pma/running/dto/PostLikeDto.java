package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostLikeDto implements Serializable {

    private Long post_id;
    private String username;


    public PostLikeDto(Long post_id, String username) {
        this.post_id = post_id;
        this.username = username;
    }

    public PostLikeDto(){}
}
