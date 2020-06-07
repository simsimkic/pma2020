package com.pma.running.service;

import com.pma.running.dto.FriendDto;
import com.pma.running.dto.FriendshipRequestDto;
import com.pma.running.model.FriendshipRequest;
import com.pma.running.model.FriendshipRequestNotification;
import com.pma.running.model.NotificationType;
import com.pma.running.model.User;
import com.pma.running.repository.FriendshipRepository;
import com.pma.running.repository.FriendshipRequestNotificationRepository;
import com.pma.running.repository.NotificationRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private FriendshipRequestNotificationRepository friendshipRequestNotificationRepository;

    public ArrayList<FriendDto> getUsers(String username){
        User loginUser = userRepository.findByUsername(username);
        ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();
        ArrayList<FriendDto> users = new ArrayList<>();
        //prodjem kroz sve usere i proverim one sa kojima je ulogovani korisnik prijatelj, a sa kojima nije
        for (User u : allUsers) {
            //pitamo da li je prijatelj sa ulogovanim
            FriendshipRequest fr = friendshipRepository.findByFriendshipRequestorAndFriendshipRequesteeAndDeleteOrFriendshipRequestorAndFriendshipRequesteeAndDelete(loginUser, u,false, u, loginUser, false);
            if (fr!= null){
                if (fr.isApproved()==false && fr.getFriendshipRequestor().getUsername().equals(username)){
                    users.add(new FriendDto(u.getName(), u.getUsername(), 2));
                }else if (fr.isApproved()==false && fr.getFriendshipRequestee().getUsername().equals(username))
                    users.add(new FriendDto(u.getName(), u.getUsername(), 3));
                else
                users.add(new FriendDto(u.getName(), u.getUsername(), 1));
            }else if(fr==null && !(u.getUsername().equals(username)))
                users.add(new FriendDto(u.getName(), u.getUsername(), 0));
        }

        return users;
    }

    public ArrayList<FriendDto> getFriends(String username) {
        User loginUser = userRepository.findByUsername(username);

        //naci sve zahteve za prijateljstvo koji su approved
        ArrayList<FriendshipRequest> friendshipRequests = (ArrayList<FriendshipRequest>) friendshipRepository.findByApprovedAndFriendshipRequestorAndDeleteOrApprovedAndFriendshipRequesteeAndDelete(true, loginUser,false,  true, loginUser, false);

        ArrayList<FriendDto> friends = new ArrayList<>();

        User friend = null;
        for(FriendshipRequest fr : friendshipRequests){
            if(fr.getFriendshipRequestor().getUsername().equals(username)) {
                friend = fr.getFriendshipRequestee();
                friends.add(new FriendDto(friend.getName(), friend.getUsername(), 1));
            }else {
                friend = fr.getFriendshipRequestor();
                friends.add(new FriendDto(friend.getName(), friend.getUsername(), 1));
            }
        }

        return  friends;
    }

    public String sendRequest(FriendshipRequestDto friendship_dto) {
        //pronadjemo 2 usera i napravimo zahtev za prijateljstvo i to sacuvamo u bazi
        User requestor = this.userRepository.findByUsername(friendship_dto.getRequestor());
        User requestee = this.userRepository.findByUsername(friendship_dto.getRequestee());


        FriendshipRequest newFriendshipRequest = new FriendshipRequest(requestee, requestor, new Date(new java.util.Date().getTime()), false);
        newFriendshipRequest = this.friendshipRepository.save(newFriendshipRequest);

        FriendshipRequestNotification notification = new FriendshipRequestNotification(new Date(new java.util.Date().getTime()), NotificationType.FRIENDSHIP_REQUEST, requestor.getName() + " sent you a friendship request", newFriendshipRequest);

        this.notificationRepository.save(notification);
        return "Successfully send friendship request";
    }

    public String acceptOrDeclineRequest(FriendshipRequestDto friendshipRequestDto) {
        //pronajdemo zahtev i promenimo mu status
        User requestor = this.userRepository.findByUsername(friendshipRequestDto.getRequestor());
        User requestee = this.userRepository.findByUsername(friendshipRequestDto.getRequestee());

        FriendshipRequest foundRequest = this.friendshipRepository.findByFriendshipRequestorAndFriendshipRequesteeAndDelete(requestor, requestee, false);
        foundRequest.setApproved(friendshipRequestDto.getAccept());
        if(foundRequest != null){
            //dodamo i notifikaciju
            if (friendshipRequestDto.getAccept()){

                foundRequest = this.friendshipRepository.save(foundRequest);
                FriendshipRequestNotification notification = new FriendshipRequestNotification(new Date(new java.util.Date().getTime()), NotificationType.APPROVED_FRIENDSHIP, requestee.getUsername() + " accepted your friend request", foundRequest);
                this.notificationRepository.save(notification);
                return notification.getDescription();
            }else {
                //izbrisemo zahtev za prijateljstvo
                //moramo izbrisati i notifikaciju
                foundRequest.setDelete(true);
                friendshipRepository.save(foundRequest);
            }

        }

        return "";
    }
}
