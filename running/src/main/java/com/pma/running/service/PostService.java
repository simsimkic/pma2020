package com.pma.running.service;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.CommentDto;
import com.pma.running.dto.PostDto;
import com.pma.running.dto.PostLikeDto;
import com.pma.running.model.*;
import com.pma.running.repository.*;
import javassist.NotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    private final LikeMeRepository likeMeRepository;
    private final CommentRepository commentRepository;
    private  final  NotificationRepository notificationRepository;


    @Autowired
    public PostService(PostRepository postRepository, ActivityService activityService,
                       UserSettingsService userSettingsService, UserRepository userRepository,
                       FriendsRepository friendsRepository, LikeMeRepository likeMeRepository,NotificationRepository notificationRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.activityService = activityService;
        this.userSettingsService = userSettingsService;
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
        this.likeMeRepository = likeMeRepository;
        this.commentRepository = commentRepository;
        this.notificationRepository = notificationRepository;
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
                List<CommentDto> commentDtos = getComment(p);
                LikeMe likeMe = likeMeRepository.findByPostAndUser(p, loginUser);
                PostDto res = new PostDto(p.getId(), p.getDescription(), p.getVisibility().ordinal(),p.getLike_num(), commentDtos.size(), p.getDate().format(formatter), p.getActivity().getEncodedMap(), p.getUser().getName(), p.getActivity().getDistance(), p.getActivity().getDuration(), commentDtos);
                if (likeMe != null){
                    res.setLike(true);
                }else {
                    res.setLike(false);
                }
                result.add(res);
            }else if (p.getVisibility() == Visibility.FRIENDS){
                //moram da proverim da li je prijatelj sa ulogovanim korisnikom
                Friends friend = friendsRepository.findByUser1AndUser2OrUser2AndUser1(loginUser, p.getUser(), loginUser, p.getUser());
                if (friend!=null){
                    List<CommentDto> commentDtos = getComment(p);
                    LikeMe likeMe = likeMeRepository.findByPostAndUser(p, loginUser);
                    PostDto res = new PostDto(p.getId(), p.getDescription(), p.getVisibility().ordinal(),p.getLike_num(), commentDtos.size(), p.getDate().format(formatter), p.getActivity().getEncodedMap(), p.getUser().getName(), p.getActivity().getDistance(), p.getActivity().getDuration(), commentDtos);
                    if (likeMe != null){
                        res.setLike(true);
                    }else {
                        res.setLike(false);
                    }
                    result.add(res);
                }
            }
        }

       // Collections.sort(result, (Post p1, Post p2) -> p1.getDate().compareTo(p2.getDate()));
        return  result;
    }

    private List<CommentDto> getComment(Post p) {
        List<Comment> comments = commentRepository.findByPost(p);
        ArrayList<CommentDto> commentDtos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Comment c: comments) {
            commentDtos.add(new CommentDto(c.getUser().getName(), c.getTimestamp().format(formatter), c.getText()));
        }
        return commentDtos;
    }

    public List<PostDto> likeOrDislikePost(PostLikeDto postLikeDto) throws NotFoundException {
        //prvo treba da pogledamo da li postoji post sa prosledjenim id-om
        Post post = postRepository.findById(postLikeDto.getPost_id()).orElseThrow(()->new NotFoundException(""));

        User user = userRepository.findByUsername(postLikeDto.getUsername());

        //pronadjemo da li postoji ili ne like od ovog korisnika za ovaj post
        LikeMe like = this.likeMeRepository.findByPostAndUser(post, user);

        if (like != null){
            //ako postoji treba da izbrisemo like i da oduzmemo broj lajkova iz post-a
            likeMeRepository.delete(like);
            post.setLike_num(post.getLike_num()-1);
            postRepository.save(post);
            return getAllPostByUser(user.getUsername());
        }

        like = new LikeMe(user, post);
        likeMeRepository.save(like);
        post.setLike_num(1+post.getLike_num());
        postRepository.save(post);
        //treba napraviti i notifikaciju, notifikacija pripada korisniku ciji je post
        Notification like_notification = new Notification(LocalDateTime.now(), NotificationType.LIKE_ON_POST, user.getName() + " like your post.", post.getUser());
        notificationRepository.save(like_notification);
         return getAllPostByUser(user.getUsername());



    }

    public List<PostDto> comment(CommentDto commentDto) throws NotFoundException {

        //pronadjemo usera i post i onda postavimo komentar
        User user = userRepository.findByUsername(commentDto.getUser());
        Post post = postRepository.findById(commentDto.getPost_id()).orElseThrow(()-> new NotFoundException(""));

        //postoji i korisnik i post, dodamo komentar
        if (user == null){
            throw  new NotFoundException("User not found");
        }

        Comment comment = new Comment(commentDto.getComment(),  LocalDateTime.now() , user, post);
        commentRepository.save(comment);
        //pravimo notifikaciju za komentar
        Notification comment_not = new Notification(LocalDateTime.now(), NotificationType.COMMENT_ON_POST, user.getName() + " comment your post.", post.getUser());
        notificationRepository.save(comment_not);
        return getAllPostByUser(user.getUsername());

    }
}
