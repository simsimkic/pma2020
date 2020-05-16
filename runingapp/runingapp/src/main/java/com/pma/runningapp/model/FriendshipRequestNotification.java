package com.pma.runningapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("friendship_request")
@Data
public class FriendshipRequestNotification extends Notification {

    @OneToOne
    @JoinColumn(name = "friendship_id")
    private FriendshipRequest friendshipRequest;
}
