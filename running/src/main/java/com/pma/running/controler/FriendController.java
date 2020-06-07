package com.pma.running.controler;

import com.pma.running.dto.FriendDto;
import com.pma.running.dto.FriendshipRequestDto;
import com.pma.running.model.FriendshipRequest;
import com.pma.running.service.FriendService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "/friends", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping(value = "/get/{username}")
    public ArrayList<FriendDto> getFriends(@PathVariable String username){
        return friendService.getFriends(username);
    }

    @GetMapping(value = "/get/users/{username}")
    public ArrayList<FriendDto> getUsers(@PathVariable String username){
        return friendService.getUsers(username);
    }

    @PostMapping(value = "/sendRequest")
    public String sendRequest(@RequestBody FriendshipRequestDto friendship_dto) {return friendService.sendRequest(friendship_dto);}

    @PostMapping(value = "/acceptOrDeclineRequest")
    public String acceptOrDeclineRequest(@RequestBody FriendshipRequestDto friendshipRequestDto) {return  this.friendService.acceptOrDeclineRequest(friendshipRequestDto);}
}
