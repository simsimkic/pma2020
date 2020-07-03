package com.pma.running.service;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.GroupActivityAnswerDto;
import com.pma.running.dto.GroupActivityDto;
import com.pma.running.model.*;
import com.pma.running.repository.ActivityRepository;
import com.pma.running.repository.ActivityRequestRepository;
import com.pma.running.repository.NotificationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserService userService;
    private  final ActivityRequestRepository activityRequestRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, UserService userService, ActivityRequestRepository activityRequestRepository, NotificationRepository notificationRepository) {
        this.activityRepository = activityRepository;
        this.userService = userService;
        this.activityRequestRepository = activityRequestRepository;
        this.notificationRepository = notificationRepository;
    }

    public Activity save(ActivityDto activityDto) {
        System.out.println(activityDto.getDateTime());
        Activity activity = new Activity();
        activity.setDuration(activityDto.getDuration());
        activity.setDistance(activityDto.getDistance());
        activity.setDateTime(LocalDateTime.parse(activityDto.getDateTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
        activity.setSteps(activityDto.getSteps());
        activity.setUser(userService.findByUsername(activityDto.getUsername()));
        activity.setEncodedMap(activityDto.getEncodedMap());
        return activityRepository.save(activity);
    }

    public Set<Activity> findActivityByUserId(Long id) {
        return activityRepository.findByUserId(id);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    public Activity delete(Long userId, Long activityId) {
        Activity activity = activityRepository.findByUserIdAndId(userId, activityId);
        activityRepository.delete(activity);
        return activity;
    }

    public GroupActivityDto sendActivityRequest(GroupActivityDto groupActivityDto) throws NotFoundException {

        User loginUser = userService.findByUsername(groupActivityDto.getUsername_requestor()); //posiljalac zahteva
        User requestee = userService.findByUsername(groupActivityDto.getUsername_requestee()); //primalac zahteva


        if (loginUser == null || requestee == null){
            throw new NotFoundException("User not exists!");
        }

        //treba da napravimo localdate na osnovu prosledjenih parametara
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime timestamp = LocalDateTime.parse(groupActivityDto.getTimestamp(), formatter);

        //napravimo zahtev i sacuvamo ga
        ActivityRequest activityRequest = new ActivityRequest(timestamp, groupActivityDto.getLocation(), ActivityRequestStatus.SEND, requestee, loginUser);
        activityRequest = this.activityRequestRepository.save(activityRequest);

        //napravimo i notifikaciju i sacuvamo je
        ActivityRequestNotification notification = new ActivityRequestNotification(LocalDateTime.now(), NotificationType.ACTIVITY_REQUEST, loginUser.getName() + " sent you an activity invitation.", requestee, activityRequest);
        notificationRepository.save(notification);

        groupActivityDto.setId(activityRequest.getId());
        return groupActivityDto;

    }

    public String acceptOrDeclineRequest(GroupActivityAnswerDto groupActivityAnswerDto) throws NotFoundException {
        //prvo proverimo da li postoji zahtev sa prosledjenim id-om
        ActivityRequest activityRequest = this.activityRequestRepository.findById(groupActivityAnswerDto.getId()).orElseThrow(() -> new NotFoundException("Activity request not found!"));

        if(groupActivityAnswerDto.getAccept()) {
            //zahtev prihvacen, menjamo status i kreiramo odgovrajucu notifikaciju
            activityRequest.setStatus(ActivityRequestStatus.ACCEPT);
            activityRequest = this.activityRequestRepository.save(activityRequest);
            ActivityRequestNotification notification = new ActivityRequestNotification(LocalDateTime.now(), NotificationType.APPROVED_ACTIVITY_REQUEST, activityRequest.getActivityRequestee().getName() + " accepted your activity invitation.", activityRequest.getActivityRequestor(), activityRequest);
            notificationRepository.save(notification);
            return "Successfully accepted the activity request!";
        }

        activityRequest.setStatus(ActivityRequestStatus.DECLINE);
        activityRequest = this.activityRequestRepository.save(activityRequest);
        ActivityRequestNotification notification = new ActivityRequestNotification(LocalDateTime.now(), NotificationType.REJECTED_ACTIVITY_REQUEST, activityRequest.getActivityRequestee().getName() + " reject your activity invitation.", activityRequest.getActivityRequestor(), activityRequest);
        notificationRepository.save(notification);
        return  "Successfully rejected the activity request!";

    }
}
