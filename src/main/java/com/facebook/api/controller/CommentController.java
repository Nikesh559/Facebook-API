package com.facebook.api.controller;

import com.facebook.api.dto.CommentDTO;
import com.facebook.api.model.Comment;
import com.facebook.api.model.Message;
import com.facebook.api.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentService;

    private final static Integer MAX_LIMIT = 5;

    @ApiOperation("getComments")
    @GetMapping("/profile/{profileId}/post/{postId}/comments")
    public List<Comment> getComments(@PathVariable("profileId") String profileId,
                                     @PathVariable("postId") Long postId,
                                     @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit,
                                     @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset) {

        limit = Math.min(limit, MAX_LIMIT);
        System.out.println(limit+" "+offset);
        if(limit > 0 && offset >= 0) {
            return commentService.getPaginatedComments(profileId, postId, limit, offset);
        }
        else
            return commentService.getComments(profileId, postId);
    }


    @ApiOperation(value="getComment")
    @GetMapping(value = "/profile/{profileId}/post/{postId}/comment/{commentId}")
    public Comment getComment(@PathVariable("profileId") String profileId,
                              @PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId) {
        return commentService.getComment(profileId, postId, commentId);
    }

    @ApiOperation("commentOnPost")
    @PostMapping("/profile/{profileId}/post/{postId}/comment")
    public ResponseEntity createComment(@PathVariable("profileId") String profileId,
                                        @PathVariable("postId") Long postId,
            @RequestBody @Valid CommentDTO comment) {
        if(commentService.createComment(profileId, postId,comment)) {
            return new ResponseEntity(new Message(HttpStatus.CREATED, "Comment added to Post"), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity(new Message(HttpStatus.BAD_REQUEST, "User/Post doesn't exists"), HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("deleteComment")
    @DeleteMapping("/profile/{profileId}/post/{postId}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("profileId") String profileId,
                                        @PathVariable("postId") Long postId,
                                        @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(profileId, postId, commentId);
        return new ResponseEntity(new Message(HttpStatus.OK, "Comment deleted from Post"), HttpStatus.OK);
    }
}
