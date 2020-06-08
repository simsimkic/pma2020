package com.pma.running.repository;

import com.pma.running.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByUserIdAndId(Long userId, Long activityId);
}
