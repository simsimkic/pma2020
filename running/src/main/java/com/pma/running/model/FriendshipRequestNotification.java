package com.pma.running.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("friendship_request")
@Data
public class FriendshipRequestNotification extends Notification {
    @OneToOne
    @JoinColumn(name = "friendship_id")
    private FriendshipRequest friendshipRequest;

    public FriendshipRequestNotification() {
    }

    public FriendshipRequestNotification(LocalDateTime timestamp, NotificationType notificationType, String description, User user, FriendshipRequest friendshipRequest) {
        super(timestamp, notificationType, description, user);
        this.friendshipRequest = friendshipRequest;
    }

    public FriendshipRequestNotification(LocalDateTime timestamp, NotificationType notificationType, String description, FriendshipRequest friendshipRequest) {
        super(timestamp, notificationType, description);
        this.friendshipRequest = friendshipRequest;
    }

    public FriendshipRequestNotification(Long id, LocalDateTime timestamp, NotificationType notificationType, String description, FriendshipRequest friendshipRequest) {
        super(id, timestamp, notificationType, description);
        this.friendshipRequest = friendshipRequest;
    }
}
