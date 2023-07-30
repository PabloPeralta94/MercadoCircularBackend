package com.mercado.circular.repository;
import com.mercado.circular.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);
    Long countByUser_NombreUsuario(String user);

    Post findByUser_NombreUsuario(String user);

    Post findByUser_NombreUsuarioAndTitle(String user, String title);

    Post findByUser_NombreUsuarioOrTitle(String user, String title);


    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByUser_NombreUsuarioAndTitleContainingIgnoreCase(String user, String title);

    List<Post> findPostsByUser_NombreUsuario(String user, Pageable pageable);


}