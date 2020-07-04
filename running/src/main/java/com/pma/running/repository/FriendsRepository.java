package com.pma.running.repository;

import com.pma.running.model.Friends;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

    List<Friends> findByUser1OrUser2(User user1, User user2);

    Friends findByUser1AndUser2OrUser2AndUser1(User u1, User u2, User u21, User u12);
}
