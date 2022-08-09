package com.facebook.api.controller;

import com.facebook.api.dto.PostDTO;
import com.facebook.api.exceptionhandling.ErrorMessage;
import com.facebook.api.model.Message;
import com.facebook.api.model.Post;
import com.facebook.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping("profile/{profileId}/post")
    public ResponseEntity createPost(@PathVariable("profileId") String profileId, @Valid @RequestBody PostDTO postDTO, BindingResult result) {
        if(postService.createPost(profileId, postDTO)){
            return new ResponseEntity(new Message(HttpStatus.CREATED, "Post created"), HttpStatus.CREATED);
        }
        else {
            List errors = result.getAllErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, errors.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
