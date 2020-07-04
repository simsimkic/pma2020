package com.pma.running.repository;

import com.pma.running.model.ActivityRequest;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRequestRepository extends JpaRepository<ActivityRequest, Long> {

    List<ActivityRequest> findByActivityRequesteeOrActivityRequestor(User requestee, User requestor);
}
