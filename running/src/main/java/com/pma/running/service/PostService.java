package com.pma.running.service;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.PostDto;
import com.pma.running.model.*;
import com.pma.running.repository.FriendsRepository;
import com.pma.running.repository.PostRepository;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ActivityService activityService;
    private final UserSettingsService userSettingsService;

    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;



    @Autowired
    public PostService(PostRepository postRepository, ActivityService activityService,
                       UserSettingsService userSettingsService, UserRepository userRepository,
                       FriendsRepository friendsRepository) {
        this.postRepository = postRepository;
        this.activityService = activityService;
        this.userSettingsService = userSettingsService;
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
    }

    public Post save(ActivityDto activityDto, Long activityId) {
        Activity activity = activityService.findById(activityId);
        Post post = new Post();
        post.setDescription(activityDto.getDescription());
        post.setActivity(activity);
        post.setUser(activity.getUser());
        post.setDate(activity.getDateTime());
        post.setVisibility(userSettingsService.findByUser(activity.getUser()).getPostPrivacy());
        return postRepository.save(post);
    }

    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    public Activity delete(Long userId, Long activityId) {
        Post post = postRepository.findByUserIdAndActivityId(userId, activityId);
        if (post == null) {
            return null;
        }
        postRepository.delete(post);
        return activityService.delete(userId, activityId);
    }

    public List<PostDto> getAllPostByUser(String username){
        User loginUser = userRepository.findByUsername(username);
        //izvucem sve postove iz baze
        List<Post> posts = postRepository.findAll();
        //prodjem kroz sve postove
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<PostDto> result = new ArrayList<>();
        for (Post p: posts) {
            //sve public postove ili postove od ulogovanog odmah dodam u listu dodajem u listu
            if (p.getVisibility() == Visibility.PUBLIC || p.getUser().getUsername().equals(loginUser.getUsername())){
                result.add(new PostDto(p.getId(), p.getDescription(), 0,p.getLike_num(), 0, p.getDate().format(formatter), p.getActivity().getEncodedMap(), p.getUser().getName(), p.getActivity().getDistance(), p.getActivity().getDuration()));
            }else if (p.getVisibility() == Visibility.FRIENDS){
                //moram da proverim da li je prijatelj sa ulogovanim korisnikom
                Friends friend = friendsRepository.findByUser1AndUser2OrUser2AndUser1(loginUser, p.getUser(), loginUser, p.getUser());
                if (friend!=null){
                    result.add(new PostDto(p.getId(), p.getDescription(), 0,p.getLike_num(),0, p.getDate().format(formatter), p.getActivity().getEncodedMap(), p.getUser().getName(), p.getActivity().getDistance(), p.getActivity().getDuration()));
                }
            }
        }

       // Collections.sort(result, (Post p1, Post p2) -> p1.getDate().compareTo(p2.getDate()));
        return  result;
    }
}
