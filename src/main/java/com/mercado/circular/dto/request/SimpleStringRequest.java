package com.mercado.circular.dto.request;

public class SimpleStringRequest {

    private String textChars;

    public SimpleStringRequest() {
    }

    public SimpleStringRequest(String textChars) {
        this.textChars = textChars;
    }

    public String getTextChars() {
        return textChars;
    }

    public void setTextChars(String textChars) {
        this.textChars = textChars;
    }
}
