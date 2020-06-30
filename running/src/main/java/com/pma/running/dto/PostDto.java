package com.pma.running.dto;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.util.List;

@Data
public class PostDto implements Serializable {
    private Long id;
    private String description;
    private int visibility;
    private int like_num;
    private int comment_num;
    private String date;
    private String bitmap;
    private String user;
    private double distance;
    private double duration;
    private List<CommentDto> comments;
    private boolean like;

    public PostDto() {
    }

    public PostDto(Long id, String description, int visibility, int like_num, int comment_num, String date, String bitmap, String user, double distance, double duration) {
        this.id = id;
        this.description = description;
        this.visibility = visibility;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.date = date;
        this.bitmap = bitmap;
        this.user = user;
        this.distance = distance;
        this.duration = duration;
    }

    public PostDto(Long id, String description, int visibility, int like_num, int comment_num, String date, String bitmap, String user, double distance, double duration, List<CommentDto> comments) {
        this.id = id;
        this.description = description;
        this.visibility = visibility;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.date = date;
        this.bitmap = bitmap;
        this.user = user;
        this.distance = distance;
        this.duration = duration;
        this.comments = comments;
    }

    public PostDto(Long id, String description, int visibility, int like_num, int comment_num, String date, String bitmap, String user, double distance, double duration, List<CommentDto> comments, boolean like) {
        this.id = id;
        this.description = description;
        this.visibility = visibility;
        this.like_num = like_num;
        this.comment_num = comment_num;
        this.date = date;
        this.bitmap = bitmap;
        this.user = user;
        this.distance = distance;
        this.duration = duration;
        this.comments = comments;
        this.like = like;
    }
}
