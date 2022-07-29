package com.facebook.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class Message {
    private int status;
    private String message;
    private String description;

    public Message(HttpStatus status, String description) {
        this.description = description;
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
