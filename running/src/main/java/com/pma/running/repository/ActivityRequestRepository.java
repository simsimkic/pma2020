package com.pma.running.repository;

import com.pma.running.model.ActivityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRequestRepository extends JpaRepository<ActivityRequest, Long> {
}
