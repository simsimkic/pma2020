package com.pma.running.service;

import com.pma.running.dto.NotificationDto;
import com.pma.running.dto.NotificationType;
import com.pma.running.model.FriendshipRequestNotification;
import com.pma.running.model.FriendshipStatus;
import com.pma.running.model.Notification;
import com.pma.running.model.User;
import com.pma.running.repository.NotificationRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            switch (n.getNotificationType()){
                case APPROVED_FRIENDSHIP:
                    dto.setType(com.pma.running.dto.NotificationType.ACCEPT_FRIEND);
                    break;
                case FRIENDSHIP_REQUEST:
                    FriendshipRequestNotification frn = (FriendshipRequestNotification)n;
                    if(frn.getFriendshipRequest().getStatus()== FriendshipStatus.SEND_REQUEST)
                        dto.setType(com.pma.running.dto.NotificationType.SEND_FRIEND);
                    break;
                case COMMENT_ON_POST:
                    dto.setType(com.pma.running.dto.NotificationType.COMMENT);
                    break;
                case LIKE_ON_POST:
                    dto.setType(com.pma.running.dto.NotificationType.LIKE);
                    break;
                case ACTIVITY_REQUEST:
                    dto.setType(com.pma.running.dto.NotificationType.SEND_INVITATION);
                    break;
            }
            result.add(dto);

        }

        return result;
    }
}
