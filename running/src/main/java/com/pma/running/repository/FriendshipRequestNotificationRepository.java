package com.pma.running.repository;

import com.pma.running.model.FriendshipRequest;
import com.pma.running.model.FriendshipRequestNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FriendshipRequestNotificationRepository extends JpaRepository<FriendshipRequestNotification, Long> {

    List<FriendshipRequestNotification> findByFriendshipRequest(FriendshipRequest fr);
}
