package com.pma.running.repository;

import com.pma.running.model.Activity;
import com.pma.running.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Set<Activity> findByUserId(Long id);
}
