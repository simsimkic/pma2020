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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visibility getUserInfoPrivacy() {
        return userInfoPrivacy;
    }

    public void setUserInfoPrivacy(Visibility userInfoPrivacy) {
        this.userInfoPrivacy = userInfoPrivacy;
    }

    public Visibility getPostPrivacy() {
        return postPrivacy;
    }

    public void setPostPrivacy(Visibility postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    public Visibility getGoalPrivacy() {
        return goalPrivacy;
    }

    public void setGoalPrivacy(Visibility goalPrivacy) {
        this.goalPrivacy = goalPrivacy;
    }

    public boolean isNewComments() {
        return newComments;
    }

    public void setNewComments(boolean newComments) {
        this.newComments = newComments;
    }

    public boolean isNewLikes() {
        return newLikes;
    }

    public void setNewLikes(boolean newLikes) {
        this.newLikes = newLikes;
    }

    public boolean isFriendshipRequest() {
        return friendshipRequest;
    }

    public void setFriendshipRequest(boolean friendshipRequest) {
        this.friendshipRequest = friendshipRequest;
    }

    public boolean isAcceptedFriendship() {
        return acceptedFriendship;
    }

    public void setAcceptedFriendship(boolean acceptedFriendship) {
        this.acceptedFriendship = acceptedFriendship;
    }

    public boolean isActivityRequest() {
        return activityRequest;
    }

    public void setActivityRequest(boolean activityRequest) {
        this.activityRequest = activityRequest;
    }

    public boolean isAcceptedActivity() {
        return acceptedActivity;
    }

    public void setAcceptedActivity(boolean acceptedActivity) {
        this.acceptedActivity = acceptedActivity;
    }

    public boolean isCanceledActivity() {
        return canceledActivity;
    }

    public void setCanceledActivity(boolean canceledActivity) {
        this.canceledActivity = canceledActivity;
    }

    public boolean isNightTheme() {
        return nightTheme;
    }

    public void setNightTheme(boolean nightTheme) {
        this.nightTheme = nightTheme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
