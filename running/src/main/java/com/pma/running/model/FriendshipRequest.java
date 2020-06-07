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

    @Column
    private boolean approved;

    @Column
    private Date sendRequest;

    @ManyToOne
    @JoinColumn(name = "requestee_id", nullable = false)
    private User friendshipRequestee;

    @ManyToOne
    @JoinColumn(name = "requestor_id", nullable = false)
    private User friendshipRequestor;

    private boolean delete;


    public FriendshipRequest(boolean approved, Date sendRequest, User friendshipRequestee, User friendshipRequestor, boolean delete) {
        this.approved = approved;
        this.sendRequest = sendRequest;
        this.friendshipRequestee = friendshipRequestee;
        this.friendshipRequestor = friendshipRequestor;
        this.delete = delete;
    }

    public FriendshipRequest(User requestee, User requestor, Date date, boolean approved) {
        this.friendshipRequestee = requestee;
        this.friendshipRequestor = requestor;
        this.sendRequest = date;
        this.approved = approved;
        this.delete = false;


    }

    public FriendshipRequest() {
    }

    public FriendshipRequest(Long id, boolean approved, Date sendRequest, User friendshipRequestee, User friendshipRequestor, boolean delete) {
        this.id = id;
        this.approved = approved;
        this.sendRequest = sendRequest;
        this.friendshipRequestee = friendshipRequestee;
        this.friendshipRequestor = friendshipRequestor;
        this.delete = delete;
    }
}
