package com.pma.running.controler;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.ActivityResponseDto;
import com.pma.running.dto.GroupActivityAnswerDto;
import com.pma.running.dto.GroupActivityDto;
import com.pma.running.model.Activity;
import com.pma.running.model.Post;
import com.pma.running.repository.PostRepository;
import com.pma.running.service.ActivityService;
import com.pma.running.service.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {

    private final ActivityService activityService;
    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public ActivityController(ActivityService activityService, PostService postService,
                              PostRepository postRepository) {
        this.activityService = activityService;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @PostMapping("/activities/share")
    public ResponseEntity<Post> shareActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.save(activityDto);
        return new ResponseEntity<>(postService.save(activityDto, activity.getId()), HttpStatus.OK);
    }

    @GetMapping("/getActivitiesByUser/{userId}")
    public ResponseEntity<Set<ActivityResponseDto>> getActivitiesByUser(@PathVariable Long userId) {
        Set<Activity> activity = activityService.findActivityByUserId(userId);
        Set<ActivityResponseDto> response = new HashSet<>();
        if (activity == null) {
            return new ResponseEntity<Set<ActivityResponseDto>>(response, HttpStatus.OK);
        }
        for (Activity act: activity) {
            ActivityResponseDto responseO = new ActivityResponseDto();
            Post post = postRepository.findByActivityId(act.getId());
            responseO.setDistance(act.getDistance());
            responseO.setDuration(act.getDuration());
            responseO.setEncodedMap(act.getEncodedMap());
            responseO.setDateTime(act.getDateTime().toString());
            responseO.setId(act.getId());
            responseO.setDescription(post.getDescription());
            response.add(responseO);
        }

        return new ResponseEntity<Set<ActivityResponseDto>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{userId}/{activityId}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable Long userId, @PathVariable Long activityId) {
        Activity deletedActivity = postService.delete(userId, activityId);
        if (deletedActivity != null) {
            return new ResponseEntity<Activity>(deletedActivity, HttpStatus.OK);
        }
        return new ResponseEntity<Activity>(deletedActivity, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/send_activity_request")
    public ResponseEntity<GroupActivityDto> sendActivityRequest(@RequestBody GroupActivityDto groupActivityDto){
        System.out.println("Send activity request");
        GroupActivityDto activityDto = null;
        try {
            activityDto = this.activityService.sendActivityRequest(groupActivityDto);
            return new ResponseEntity<>(activityDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/group_activity/accept_decline")
    public ResponseEntity<List<GroupActivityDto>> acceptOrDeaclineRequest(@RequestBody GroupActivityAnswerDto groupActivityAnswerDto){
        String accept = groupActivityAnswerDto.getAccept() ? "accept" : "decline";
        System.out.println(accept  + "request");

        try {
            List<GroupActivityDto> activityDtos = this.activityService.acceptOrDeclineRequest(groupActivityAnswerDto);
            return  new ResponseEntity<>(activityDtos, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/get/group_activity/{username}")
    public List<GroupActivityDto> getGroupActivity(@PathVariable String username){
        return this.activityService.getGroupActivity(username);
    }

}
