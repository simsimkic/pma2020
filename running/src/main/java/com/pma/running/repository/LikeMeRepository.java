package com.pma.running.repository;

import com.pma.running.model.LikeMe;
import com.pma.running.model.Post;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMeRepository extends JpaRepository< LikeMe, Long> {

    LikeMe findByPostAndUser(Post post, User user);

}
