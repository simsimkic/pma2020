package com.pma.running.service;

import com.pma.running.dto.NotificationSettingsDto;
import com.pma.running.dto.PrivacySettingsDto;
import com.pma.running.dto.ThemeSettingsDto;
import com.pma.running.dto.UserSettingsResponseDto;
import com.pma.running.model.User;
import com.pma.running.model.UserSettings;
import com.pma.running.model.Visibility;
import com.pma.running.model.Post;
import com.pma.running.repository.PostRepository;
import com.pma.running.repository.UserRepository;
import com.pma.running.repository.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserSettingsService(UserSettingsRepository userSettingsRepository,
                               UserRepository userRepository, PostRepository postRepository) {
        this.userSettingsRepository = userSettingsRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public UserSettings findByUser(User user) {
        return userSettingsRepository.findByUser(user);
    }

    public UserSettings save(UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }

    public UserSettingsResponseDto updatePrivacySettings(PrivacySettingsDto dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        UserSettings userSettings = findByUser(user);
        userSettings.setUserInfoPrivacy(Visibility.valueOf(dto.getUserInfoPrivacy().toUpperCase()));
        Visibility postVisibility = Visibility.valueOf(dto.getPostPrivacy().toUpperCase());
        userSettings.setPostPrivacy(postVisibility);
        userSettings.setGoalPrivacy(Visibility.valueOf(dto.getGoalPrivacy().toUpperCase()));

        List<Post> posts = postRepository.findAllByUser(user);
        for (Post post : posts) {
            post.setVisibility(postVisibility);
            postRepository.save(post);
        }

        return convertUserSettingsToDto(save(userSettings));
    }

    public UserSettingsResponseDto updateNotificationSettings(NotificationSettingsDto dto) {
        UserSettings userSettings = findByUser(userRepository.findByUsername(dto.getUsername()));
        userSettings.setNewComments(dto.isNewComments());
        userSettings.setNewLikes(dto.isNewLikes());
        userSettings.setFriendshipRequest(dto.isFriendshipRequest());
        userSettings.setAcceptedFriendship(dto.isAcceptedFriendship());
        userSettings.setActivityRequest(dto.isActivityRequest());
        userSettings.setAcceptedActivity(dto.isAcceptedActivity());
        userSettings.setCanceledActivity(dto.isCanceledActivity());
        return convertUserSettingsToDto(save(userSettings));
    }

    public UserSettingsResponseDto updateThemeSettings(ThemeSettingsDto dto) {
        UserSettings userSettings = findByUser(userRepository.findByUsername(dto.getUsername()));
        userSettings.setNightTheme(dto.isNightTheme());
        return convertUserSettingsToDto(save(userSettings));
    }

    public UserSettingsResponseDto getUserSettings(String username) {
        UserSettings userSettings = userSettingsRepository.findByUser(userRepository.findByUsername(username));
        return convertUserSettingsToDto(userSettings);
    }

    private UserSettingsResponseDto convertUserSettingsToDto(UserSettings userSettings) {
        UserSettingsResponseDto dto = new UserSettingsResponseDto();
        dto.setUserInfoPrivacy(userSettings.getUserInfoPrivacy().toString());
        dto.setPostPrivacy(userSettings.getPostPrivacy().toString());
        dto.setGoalPrivacy(userSettings.getGoalPrivacy().toString());
        dto.setNewComments(userSettings.isNewComments());
        dto.setNewLikes(userSettings.isNewLikes());
        dto.setFriendshipRequest(userSettings.isFriendshipRequest());
        dto.setAcceptedFriendship(userSettings.isAcceptedFriendship());
        dto.setActivityRequest(userSettings.isActivityRequest());
        dto.setAcceptedActivity(userSettings.isAcceptedActivity());
        dto.setCanceledActivity(userSettings.isCanceledActivity());
        dto.setNightTheme(userSettings.isNightTheme());
        return dto;
    }
}
