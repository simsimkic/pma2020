package com.pma.running.repository;

import com.pma.running.model.FriendshipRequest;
import com.pma.running.model.FriendshipStatus;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipRequest, Long> {

    List<FriendshipRequest> findByStatusAndFriendshipRequestorOrStatusAndFriendshipRequestee(FriendshipStatus approved, User sender, FriendshipStatus approved1, User recipent);



    FriendshipRequest findByFriendshipRequestorAndFriendshipRequestee(User requestor, User requestee);

    FriendshipRequest findTopByFriendshipRequestorAndFriendshipRequesteeOrFriendshipRequestorAndFriendshipRequesteeOrderBySendRequestDesc(User loginUser, User u, User u1, User loginUser1);
}
