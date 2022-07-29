package com.facebook.api.exceptionhandling;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private int status;
    private String error;
    private String description;

    public ErrorMessage(HttpStatus status, String description) {
        this.description = description;
        this.status = status.value();
        this.error = status.getReasonPhrase();
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
        return error;
    }

    public void setMessage(String message) {
        this.error = message;
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
