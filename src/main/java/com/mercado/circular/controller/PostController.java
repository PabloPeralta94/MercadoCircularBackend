package com.mercado.circular.controller;

import com.mercado.circular.model.Post;
import com.mercado.circular.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Optional<Post> post = postService.getPostById(postId);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @Valid @RequestBody Post post) {
        Optional<Post> existingPost = postService.getPostById(postId);
        if (existingPost.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        post.setId(postId);
        Post updatedPost = postService.updatePost(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        Optional<Post> post = postService.getPostById(postId);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<Post>> getPostsByUser(@RequestParam String user) {
        List<Post> posts = postService.getPostsByUser(user);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/byTitle")
    public ResponseEntity<List<Post>> getPostsByTitle(@RequestParam String title) {
        List<Post> posts = postService.getPostsByTitle(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Paginated endpoint to get all posts
    @GetMapping("/paginated")
    public ResponseEntity<Page<Post>> getPaginatedPosts(Pageable pageable) {
        Page<Post> posts = postService.getPaginatedPosts(pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Paginated endpoint to get posts by a specific user
    @GetMapping("/byUserPaginated")
    public ResponseEntity<Page<Post>> getPaginatedPostsByUser(@RequestParam String user, Pageable pageable) {
        Page<Post> posts = postService.getPaginatedPostsByUser(user, pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // You can add more endpoints as needed based on your requirements
}
