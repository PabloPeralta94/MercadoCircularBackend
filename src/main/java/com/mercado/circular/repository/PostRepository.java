package com.mercado.circular.repository;
import com.mercado.circular.MDto.PostDTO;
import com.mercado.circular.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.mercado.circular.MDto.PostDTO(p.postId, p.title, p.text, p.user.nombreUsuario) FROM Post p")
    List<PostDTO> getAllPosts();

    @Query("SELECT new com.mercado.circular.MDto.PostDTO(p.postId, p.title, p.text, p.user.nombreUsuario) FROM Post p WHERE p.user.nombreUsuario = :nombreUsuario")
    List<PostDTO> getPostsByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
    Optional<Post> findById(Long postId);
    Long countByUser_NombreUsuario(String user);

    Optional<Post> findByUser_NombreUsuario(String user);

    Post findByUser_NombreUsuarioAndTitle(String user, String title);

    Post findByUser_NombreUsuarioOrTitle(String user, String title);


    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByUser_NombreUsuarioAndTitleContainingIgnoreCase(String user, String title);

    List<Post> findPostsByUser_NombreUsuario(String user, Pageable pageable);


}