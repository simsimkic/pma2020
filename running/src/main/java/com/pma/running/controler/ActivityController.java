package com.pma.running.controler;

import com.pma.running.dto.ActivityDto;
import com.pma.running.model.Activity;
import com.pma.running.model.Post;
import com.pma.running.service.ActivityService;
import com.pma.running.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {

    private final ActivityService activityService;
    private final PostService postService;

    @Autowired
    public ActivityController(ActivityService activityService, PostService postService) {
        this.activityService = activityService;
        this.postService = postService;
    }

    @PostMapping("/activities/share")
    public ResponseEntity<Post> shareActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.save(activityDto);
        return new ResponseEntity<>(postService.save(activityDto, activity.getId()), HttpStatus.OK);
    }

}
