package com.mercado.circular.model;
import com.mercado.circular.security.entity.Usuario;
import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    @Lob
    private String text;

    private String imgUrl;
    private String videoUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario user;


    public Post() {
    }


    public Post(String title, String text, Usuario user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public Post(String title, String text, String imgUrl, Usuario user) {
        this.title = title;
        this.text = text;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public Post(String title, String text,Usuario user, String videoUrl) {
        this.title = title;
        this.text = text;
        this.videoUrl = videoUrl;
        this.user = user;
    }

    public Post(String title, String text, String imgUrl, String videoUrl, Usuario user) {
        this.title = title;
        this.text = text;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.user = user;
    }



    public Long getId() {
        return postId;
    }

    public void setId(Long id) {
        this.postId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
