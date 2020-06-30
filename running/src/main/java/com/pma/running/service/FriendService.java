package com.pma.running.service;

import com.pma.running.dto.FriendDto;
import com.pma.running.dto.FriendshipRequestDto;
import com.pma.running.model.*;
import com.pma.running.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FriendsRepository friendsRepository;

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
            Friends friend = friendsRepository.findByUser1AndUser2OrUser2AndUser1(loginUser, u, loginUser, u);
            if (friend == null) {
                FriendshipRequest fr = friendshipRepository.findTopByFriendshipRequestorAndFriendshipRequesteeOrFriendshipRequestorAndFriendshipRequesteeOrderBySendRequestDesc(loginUser, u, u, loginUser);
                if (fr != null) {
                    if (fr.getStatus() == FriendshipStatus.SEND_REQUEST && fr.getFriendshipRequestor().getUsername().equals(username)) {
                        users.add(new FriendDto(u.getName(), u.getUsername(), 2));
                    } else if (fr.getStatus() == FriendshipStatus.SEND_REQUEST && fr.getFriendshipRequestee().getUsername().equals(username)){
                        users.add(new FriendDto(u.getName(), u.getUsername(), 3));}
                    else if(fr.getStatus()==FriendshipStatus.DECLINE_REQUEST){
                        users.add(new FriendDto(u.getName(), u.getUsername(), 0));}
                }else if(fr == null && !u.getUsername().equals(loginUser.getUsername()) ) {
                    users.add(new FriendDto(u.getName(), u.getUsername(), 0));
                }
            }else {
                users.add(new FriendDto(u.getName(), u.getUsername(), 1));
            }
        }


        return users;
    }

    public ArrayList<FriendDto> getFriends(String username) {
        User loginUser = userRepository.findByUsername(username);
        ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();
        ArrayList<FriendDto> users = new ArrayList<>();
        //prodjemo prijatelje za ulogovanog korisnika
        List<Friends> friends = this.friendsRepository.findByUser1OrUser2(loginUser, loginUser);
        FriendDto friend = null;
        for (Friends  f: friends) {
            if (f.getUser1().getUsername().equals(loginUser.getUsername())){
                friend = new FriendDto(f.getUser2().getName(), f.getUser2().getUsername(), 1);
            }else if (f.getUser2().getUsername().equals(loginUser.getUsername())){
                friend = new FriendDto(f.getUser1().getName(), f.getUser1().getUsername(), 1);
            }
            users.add(friend);
        }


        return users;

    }

    public String sendRequest(FriendshipRequestDto friendship_dto) {
        //pronadjemo 2 usera i napravimo zahtev za prijateljstvo i to sacuvamo u bazi
        User requestor = this.userRepository.findByUsername(friendship_dto.getRequestor());
        User requestee = this.userRepository.findByUsername(friendship_dto.getRequestee());


        FriendshipRequest newFriendshipRequest = new FriendshipRequest(requestee, requestor, new Date(new java.util.Date().getTime()), FriendshipStatus.SEND_REQUEST);
        newFriendshipRequest = this.friendshipRepository.save(newFriendshipRequest);

        FriendshipRequestNotification notification = new FriendshipRequestNotification(LocalDateTime.now(), NotificationType.FRIENDSHIP_REQUEST, requestor.getName() + " sent you a friendship request", requestee, newFriendshipRequest);

        this.notificationRepository.save(notification);
        return "Successfully send friendship request";
    }

    public String acceptOrDeclineRequest(FriendshipRequestDto friendshipRequestDto) {
//        pronajdemo zahtev i promenimo mu status
        User requestor = this.userRepository.findByUsername(friendshipRequestDto.getRequestor());
        User requestee = this.userRepository.findByUsername(friendshipRequestDto.getRequestee());

        FriendshipRequest foundRequest = this.friendshipRepository.findByFriendshipRequestorAndFriendshipRequestee(requestor, requestee);

        if(foundRequest != null){
            //dodamo i notifikaciju ako je prihvacen zahtev i dodamo povezemo prijatelje
            if (friendshipRequestDto.getAccept()){
                foundRequest.setStatus(FriendshipStatus.APPROVED_REQUEST);
                foundRequest = this.friendshipRepository.save(foundRequest);
                FriendshipRequestNotification notification = new FriendshipRequestNotification(LocalDateTime.now(), NotificationType.APPROVED_FRIENDSHIP, requestee.getUsername() + " accepted your friend request", requestor ,foundRequest);
                this.notificationRepository.save(notification);
                Friends friends = new Friends(foundRequest.getFriendshipRequestee(), foundRequest.getFriendshipRequestor());
                this.friendsRepository.save(friends);
                return notification.getDescription();
            }else {
                //izbrisem zahtev za prijateljstvo
                foundRequest.setStatus(FriendshipStatus.DECLINE_REQUEST);
                friendshipRepository.save(foundRequest);
            }

        }

        return "";
    }


    public String deleteFriends(FriendshipRequestDto friendshipRequestDto){
        User requestor = this.userRepository.findByUsername(friendshipRequestDto.getRequestor());
        User requestee = this.userRepository.findByUsername(friendshipRequestDto.getRequestee());

        //pronadjem zahtev u prijateljima i izbrisem ga
        Friends friends = this.friendsRepository.findByUser1AndUser2OrUser2AndUser1(requestee, requestor, requestee, requestor);
        //treba da se promeni i zahtev u decline
        List<FriendshipRequest> request = friendshipRepository.findByStatusAndFriendshipRequestorAndFriendshipRequesteeOrStatusAndFriendshipRequesteeAndFriendshipRequestor(FriendshipStatus.APPROVED_REQUEST, requestor, requestee, FriendshipStatus.APPROVED_REQUEST, requestor, requestee);
        for (FriendshipRequest r: request) {
            r.setStatus(FriendshipStatus.DECLINE_REQUEST);
            this.friendshipRepository.save(r);
        }
        if(friends!= null)
            this.friendsRepository.delete(friends);

        return "Successfully delete friends";
    }
}
