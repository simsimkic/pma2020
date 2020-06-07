package com.pma.running.repository;

import com.pma.running.model.FriendshipRequest;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipRequest, Long> {

    List<FriendshipRequest> findByApprovedAndFriendshipRequestorAndDeleteOrApprovedAndFriendshipRequesteeAndDelete(Boolean approved, User sender,boolean del1, Boolean approved1, User recipent, boolean del2);


    FriendshipRequest findByFriendshipRequestorAndFriendshipRequesteeAndDeleteOrFriendshipRequestorAndFriendshipRequesteeAndDelete(User requestor, User requestee,Boolean del1, User requestor1, User requestee1, Boolean del2);

    FriendshipRequest findByFriendshipRequestorAndFriendshipRequesteeAndDelete(User requestor, User requestee, Boolean delete);
}
