package com.mercado.circular.interfaces;

import com.mercado.circular.security.entity.Usuario;

public interface PostProjection {
    // Define the required fields that you want to project in the result
    Long getPostId();
    String getTitle();
    String getText();
    String getImgUrl();
    String getVideoUrl();
    Usuario getUser();
}
