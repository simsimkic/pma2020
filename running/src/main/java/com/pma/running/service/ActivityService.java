package com.pma.running.service;

import com.pma.running.dto.ActivityDto;
import com.pma.running.model.Activity;
import com.pma.running.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserService userService;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
    }

    public Activity save(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setDuration(activityDto.getDuration());
        activity.setDistance(activityDto.getDistance());
        activity.setDateTime(LocalDateTime.parse(activityDto.getDateTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
        activity.setSteps(activityDto.getSteps());
        activity.setUser(userService.findByUsername(activityDto.getUsername()));
        return activityRepository.save(activity);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }
}
