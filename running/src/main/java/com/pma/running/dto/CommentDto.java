package com.pma.running.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private String user;
    private String date;
    private String comment;
    private Long post_id;

    public CommentDto() {
    }

    public CommentDto(String user, String date, String comment) {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    public CommentDto(String user, String date, String comment, Long post_id) {
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.post_id = post_id;
    }
}
