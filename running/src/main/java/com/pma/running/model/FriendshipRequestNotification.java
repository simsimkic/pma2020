package com.pma.running.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
@Entity
@DiscriminatorValue("friendship_request")
@Data
public class FriendshipRequestNotification extends Notification {
    @OneToOne
    @JoinColumn(name = "friendship_id")
    private FriendshipRequest friendshipRequest;
}
