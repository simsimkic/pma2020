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
}
