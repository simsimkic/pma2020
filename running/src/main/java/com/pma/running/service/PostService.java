package com.pma.running.service;

import com.pma.running.dto.ActivityDto;
import com.pma.running.model.Activity;
import com.pma.running.model.Post;
import com.pma.running.model.User;
import com.pma.running.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ActivityService activityService;
    private final UserSettingsService userSettingsService;

    @Autowired
    public PostService(PostRepository postRepository, ActivityService activityService,
                       UserSettingsService userSettingsService) {
        this.postRepository = postRepository;
        this.activityService = activityService;
        this.userSettingsService = userSettingsService;
    }

    public Post save(ActivityDto activityDto, Long activityId) {
        Activity activity = activityService.findById(activityId);
        Post post = new Post();
        post.setDescription(activityDto.getDescription());
        post.setActivity(activity);
        post.setUser(activity.getUser());
        post.setVisibility(userSettingsService.findByUser(activity.getUser()).getPostPrivacy());
        return postRepository.save(post);
    }

    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }
}
