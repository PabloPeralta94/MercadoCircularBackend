package com.mercado.circular.service;
import com.mercado.circular.model.Post;
import com.mercado.circular.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
    public List<Post> getPostsByUser(String user) {
        Pageable pageable = null;
        return postRepository.findPostsByUser_NombreUsuario(user, null);
    }

    public List<Post> getPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Post> getPostsByUserAndTitle(String user, String title) {
        return postRepository.findByUser_NombreUsuarioAndTitleContainingIgnoreCase(user, title);
    }

    public Page<Post> getPaginatedPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getPaginatedPostsByUser(String user, Pageable pageable) {
        return (Page<Post>) postRepository.findPostsByUser_NombreUsuario(user, pageable);
    }
}