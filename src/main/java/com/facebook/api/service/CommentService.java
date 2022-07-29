package com.facebook.api.service;

import com.facebook.api.controller.CommentController;
import com.facebook.api.dto.CommentDTO;
import com.facebook.api.model.Comment;
import com.facebook.api.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapper mapper;

    public List<Comment> getComments(String profileId, Long postId) {
        if(profileService.userExists(profileId)){
            List<Comment> comments = commentRepository.getComments(postId);

            for(Comment comment : comments) {
                Link self = linkTo(methodOn(CommentController.class).getComment(profileId, postId, comment.getCommentId())).withSelfRel();
                comment.add(self);
            }
            return comments;
        }
        else
            return null;
    }

    public List<Comment> getPaginatedComments(String profileId, Long postId, int limit, int offset) {
        List<Comment> comments = getComments(profileId, postId);
        int size = comments.size();
        return offset < size ? comments.subList(offset, Math.min(offset + limit, size)) : null;
    }

    public Comment getComment(String profileId, Long postId, Long commentId) {
        if(profileService.userExists(profileId)) {
            Comment comment = commentRepository.findById(Long.valueOf(commentId)).get();
            Link self = linkTo(methodOn(CommentController.class).getComment(profileId, postId, commentId)).withSelfRel();
            comment.add(self);
            return comment;
        }
        else
            return null;
    }

    public boolean createComment(String profileId, Long postId, CommentDTO commentDTO) {
        try {
            Comment comment = mapper.map(commentDTO, Comment.class);
            comment.setPostId(postId);
            comment.setLikes(0);
            comment.setProfileId(profileId);
            commentRepository.save(comment);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteComment(String profileId, Long postId, Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
