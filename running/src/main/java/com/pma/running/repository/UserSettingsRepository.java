package com.pma.running.repository;

import com.pma.running.model.User;
import com.pma.running.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

    UserSettings findByUser(User user);
}
