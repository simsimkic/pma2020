package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Visibility userInfoPrivacy;
    @Enumerated(EnumType.ORDINAL)
    private Visibility postPrivacy;
    @Enumerated(EnumType.ORDINAL)
    private Visibility goalPrivacy;
    private boolean newComments;
    private boolean newLikes;
    private boolean friendshipRequest;
    private boolean acceptedFriendship;
    private boolean activityRequest;
    private boolean acceptedActivity;
    private boolean canceledActivity;
    private boolean nightTheme;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserSettings() { }

    public UserSettings(User user) {
        this.userInfoPrivacy = Visibility.PUBLIC;
        this.postPrivacy = Visibility.PUBLIC;
        this.goalPrivacy = Visibility.PUBLIC;
        this.newComments = true;
        this.newLikes = true;
        this.friendshipRequest = true;
        this.acceptedFriendship = true;
        this.activityRequest = true;
        this.acceptedActivity = true;
        this.canceledActivity = true;
        this.nightTheme = false;
        this.user = user;
    }
}
