package com.mercado.circular.MDto;

public class PostDTO {
    private Long postId;
    private String title;
    private String text;

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

    public PostDTO(Long postId, String title, String text) {
        this.postId = postId;
        this.title = title;
        this.text = text;
    }

    public PostDTO() {
    }

    private String imgUrl;
    private String videoUrl;

}