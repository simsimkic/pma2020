package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
public class Friends implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    public Friends() {
    }

    public Friends(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
    public Friends(Long id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

}
