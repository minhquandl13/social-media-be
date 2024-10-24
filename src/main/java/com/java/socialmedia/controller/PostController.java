package com.java.socialmedia.controller;

import com.java.socialmedia.models.Post;
import com.java.socialmedia.response.ApiResponse;
import com.java.socialmedia.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,
                                           @PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
                                                  @PathVariable Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse response = new ApiResponse(message, true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPosts(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,
                                                 @PathVariable Integer userId) throws Exception {
        Post savedPost = postService.savedPost(postId, userId);

        return new ResponseEntity<>(savedPost, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,
                                               @PathVariable Integer userId) throws Exception {
        Post likedPost = postService.likePost(postId, userId);

        return new ResponseEntity<>(likedPost, HttpStatus.ACCEPTED);
    }
}
