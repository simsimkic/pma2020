package com.pma.running.model;

import lombok.Data;
import org.omg.PortableServer.ServantActivator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class LikeMe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public LikeMe(User user, Post post) {
        this.user = user;
        this.post = post;
    }
    public LikeMe(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public LikeMe() {
    }
}
