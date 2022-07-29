package com.facebook.api.controller;

import com.facebook.api.model.Post;
import com.facebook.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/profile/{profileId}/posts")
    public List<Post> getPost(@PathVariable("profileId") String profileId) {
        return postService.getPosts(profileId);
    }

    @GetMapping("/profile/{profileId}/post/{postId}")
    public Post getPost(@PathVariable("profileId") String profileId, @PathVariable("postId") Long postId) {
        return postService.getPost(profileId, postId);
    }
}
