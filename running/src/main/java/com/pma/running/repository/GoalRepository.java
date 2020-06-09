package com.pma.running.repository;

import com.pma.running.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Set<Goal> findByUserId(Long id);

}
