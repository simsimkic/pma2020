package com.pma.running.repository;

import com.pma.running.model.Post;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
    Post findByUserIdAndActivityId(Long userId, Long activityId);


}
