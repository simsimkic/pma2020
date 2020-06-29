package com.pma.running.controler;

import com.pma.running.dto.CommentDto;
import com.pma.running.dto.PostDto;
import com.pma.running.dto.PostLikeDto;
import com.pma.running.service.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping(value = "/get/{username}")
    public List<PostDto> get(@PathVariable String username){
            return  postService.getAllPostByUser(username);
    }




    @PostMapping(value = "/like")
    public boolean likeOrDislike(@RequestBody PostLikeDto postLikeDto) {
        System.out.println("User " + postLikeDto.getUsername() + " like or dislike post: "  + postLikeDto.getPost_id() );
        try {
            return postService.likeOrDislikePost(postLikeDto);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value="/addComment")
    public boolean comment(@RequestBody CommentDto commentDto){

        System.out.println("User " + commentDto.getUser() + " comment post " + commentDto.getPost_id());

        try {
            return postService.comment(commentDto);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }
}
