package com.pma.running.service;

import com.pma.running.dto.GroupActivityDto;
import com.pma.running.dto.NotificationDto;
import com.pma.running.dto.NotificationType;
import com.pma.running.model.*;
import com.pma.running.repository.NotificationRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<NotificationDto> getNotifications(String username){
        User loginUser = userRepository.findByUsername(username);
        List<Notification> notifications = notificationRepository.findByUser(loginUser);
        List<NotificationDto> result = new ArrayList<>();
        //prodjem kroz sve notifikacije i prebacim ih u dto
        for (Notification n: notifications) {
            NotificationDto dto = new NotificationDto();
            dto.setId(n.getId());
            dto.setDescription(n.getDescription());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            dto.setDate(n.getTimestamp().format(formatter));
            if(n.getNotificationType() == com.pma.running.model.NotificationType.APPROVED_FRIENDSHIP){
                dto.setType(com.pma.running.dto.NotificationType.ACCEPT_FRIEND);
                result.add(dto);
            }else if(n.getNotificationType() == com.pma.running.model.NotificationType.FRIENDSHIP_REQUEST){
                FriendshipRequestNotification frn = (FriendshipRequestNotification)n;
                if(frn.getFriendshipRequest().getStatus()== FriendshipStatus.SEND_REQUEST) {
                    dto.setType(com.pma.running.dto.NotificationType.SEND_FRIEND);
                    dto.setFriend_username(frn.getFriendshipRequest().getFriendshipRequestor().getUsername());
                    result.add(dto);
                }
            }else if(n.getNotificationType() == com.pma.running.model.NotificationType.COMMENT_ON_POST) {

                dto.setType(com.pma.running.dto.NotificationType.COMMENT);
                result.add(dto);
            } else if(n.getNotificationType() == com.pma.running.model.NotificationType.LIKE_ON_POST){

                    dto.setType(com.pma.running.dto.NotificationType.LIKE);
                result.add(dto);
            } else if(n.getNotificationType() == com.pma.running.model.NotificationType.ACTIVITY_REQUEST) {

                ActivityRequestNotification arn = (ActivityRequestNotification) n;
                if (arn.getActivityRequest().getStatus() == ActivityRequestStatus.SEND) {
                    dto.setType(com.pma.running.dto.NotificationType.SEND_INVITATION);
                    //treba da dodam i aktivnost

                    addActivityData(dto, (ActivityRequestNotification) n);
                    result.add(dto);
                }
            }
             else if (n.getNotificationType() == com.pma.running.model.NotificationType.APPROVED_ACTIVITY_REQUEST) {


                    dto.setType(NotificationType.ACCEPT_INVITATION);
                    addActivityData(dto, (ActivityRequestNotification)n);
                result.add(dto);

            }


        }

        return result;
    }

    private void addActivityData(NotificationDto dto, ActivityRequestNotification notifications) {
        ActivityRequest activityRequest = notifications.getActivityRequest();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        GroupActivityDto groupActivityDto = new GroupActivityDto(activityRequest.getId(), notifications.getTimestamp().format(formatter) , activityRequest.getActivityRequestor().getName(), activityRequest.getActivityRequestee().getName(),  activityRequest.getLocation() );
        dto.setActivityDto(groupActivityDto);
    }
}
