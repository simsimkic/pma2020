package com.pma.running.model;

import lombok.Data;
import org.omg.PortableServer.ServantActivator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "friendship")
public class FriendshipRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column
    private Date sendRequest;

    @ManyToOne
    @JoinColumn(name = "requestee_id", nullable = false)
    private User friendshipRequestee;

    @ManyToOne
    @JoinColumn(name = "requestor_id", nullable = false)
    private User friendshipRequestor;




    public FriendshipRequest(User requestee, User requestor, Date date, FriendshipStatus status) {
        this.friendshipRequestee = requestee;
        this.friendshipRequestor = requestor;
        this.sendRequest = date;
        this.status = status;


    }

    public FriendshipRequest() {
    }

    public FriendshipRequest(Long id, FriendshipStatus status, Date sendRequest, User friendshipRequestee, User friendshipRequestor) {
        this.id = id;
        this.status = status;
        this.sendRequest = sendRequest;
        this.friendshipRequestee = friendshipRequestee;
        this.friendshipRequestor = friendshipRequestor;
    }
}
