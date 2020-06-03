package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String location;
    private String biography;
    private double height;
    private double weight;
    private String password;

    @OneToMany(mappedBy = "friendshipRequestor", cascade = CascadeType.ALL)
    private Set<FriendshipRequest> sendRequests;

    @OneToMany(mappedBy = "friendshipRequestee", cascade = CascadeType.ALL)
    private Set<FriendshipRequest> acceptRequests;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private Set<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments;


    @OneToMany(mappedBy = "activityRequestor")
    private Set<ActivityRequest> sendAcitivityRequests;

    @OneToMany(mappedBy = "activityRequestee")
    private Set<ActivityRequest> acceptActivityRequests;

    @OneToMany(mappedBy = "user")
    private Set<Goal> goals;
}
