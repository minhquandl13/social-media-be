package com.java.socialmedia.controller;

import com.java.socialmedia.models.Comment;
import com.java.socialmedia.models.User;
import com.java.socialmedia.service.CommentService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(comment, postId, user.getId());

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                               @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId, user.getId());

        return likedComment;
    }
}
