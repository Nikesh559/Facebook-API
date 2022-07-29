package com.facebook.api.service;

import com.facebook.api.controller.CommentController;
import com.facebook.api.controller.PostController;
import com.facebook.api.model.Post;
import com.facebook.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProfileService profileService;

    public Post getPost(String profileId, Long postId) {
        if(profileService.userExists(profileId)) {
            Post post = postRepository.findById(postId).get();
            addLinks(profileId, post);
            return post;
        }
        return null;
    }

    public List<Post> getPosts(String profileId) {
        List<Post> posts = postRepository.getPosts(profileId);

        for(Post post : posts) {
            addLinks(profileId,post);
        }
        return posts;
    }

    private Post addLinks(String profileId, Post post) {
        Link self = linkTo(methodOn(PostController.class).getPost(profileId, post.getPostId())).withSelfRel();
        Link comments = linkTo(methodOn(CommentController.class).getComments(profileId,post.getPostId(), null, null)).withRel("comments");
        post.add(comments);
        post.add(self);
        return post;
    }



}
