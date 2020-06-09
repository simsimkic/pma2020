package com.pma.running.controler;

import com.pma.running.dto.PostDto;
import com.pma.running.model.Post;
import com.pma.running.service.PostService;
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
}
