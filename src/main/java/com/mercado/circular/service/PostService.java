package com.mercado.circular.service;
import com.mercado.circular.MDto.PostDTO;
import com.mercado.circular.model.Post;
import com.mercado.circular.repository.PostRepository;
import com.mercado.circular.security.entity.Usuario;
import com.mercado.circular.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public List<PostDTO> getPostsByNombreUsuario(String nombreUsuario) {
        return postRepository.getPostsByNombreUsuario(nombreUsuario);
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(Post post, Usuario usuario) {
        post.setUser(usuario);
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

    @Transactional
    public Post createPostForUsuario(String nombreUsuario, String postTitle, String postText) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario with nombreUsuario " + nombreUsuario + " not found."));

        Post post = new Post(postTitle, postText, usuario);

        return postRepository.save(post);
    }
}
