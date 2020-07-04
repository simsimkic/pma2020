package com.pma.running.controler;

import com.pma.running.dto.NotificationDto;
import com.pma.running.dto.PostDto;
import com.pma.running.service.NotificationService;
import com.pma.running.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationControler {

    @Autowired
    private NotificationService notificationService;
    @GetMapping(value = "/get/{username}")
    public List<NotificationDto> get(@PathVariable String username){
        return  notificationService.getNotifications(username);
    }
}

