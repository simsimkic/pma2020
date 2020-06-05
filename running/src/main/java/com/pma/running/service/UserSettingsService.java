package com.pma.running.service;

import com.pma.running.model.User;
import com.pma.running.model.UserSettings;
import com.pma.running.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;

    @Autowired
    public UserSettingsService(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    public UserSettings findByUser(User user) {
        return userSettingsRepository.findByUser(user);
    }

    public UserSettings save(UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }
}
