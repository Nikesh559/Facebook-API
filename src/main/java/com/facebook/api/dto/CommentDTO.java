package com.facebook.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CommentDTO {
    @NotBlank
    @Min(5)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
