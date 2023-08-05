package com.mercado.circular.MDto;

public class PostDTO {
    private Long postId;
    private String title;
    private String text;
    private String nombreUsuario; // New property for nombreUsuario

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public String getNombreUsuario() { // Getter for nombreUsuario
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) { // Setter for nombreUsuario
        this.nombreUsuario = nombreUsuario;
    }

    public PostDTO(Long postId, String title, String text, String nombreUsuario) { // Constructor with nombreUsuario
        this.postId = postId;
        this.title = title;
        this.text = text;
        this.nombreUsuario = nombreUsuario;
    }

    public PostDTO() {
    }

    private String imgUrl;
    private String videoUrl;

    // Getters and setters for imgUrl and videoUrl (if needed)
}
