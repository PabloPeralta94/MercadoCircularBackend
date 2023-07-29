package com.mercado.circular.model;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    @Lob
    private String text; // Text of the posting (can be very large)

    private String imgUrl; // Link to an image (optional)
    private String videoUrl; // Link to a video (optional)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario user; // User associated with the posting

    // Default constructor
    public SocialWebPosting() {
    }

    // Constructor without optional image and video fields
    public SocialWebPosting(String title, String text, Usuario user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    // Constructor with optional image and video fields
    public SocialWebPosting(String title, String text, String imgUrl, String videoUrl, Usuario user) {
        this.title = title;
        this.text = text;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.user = user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
