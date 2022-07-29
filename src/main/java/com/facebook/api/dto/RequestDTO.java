package com.facebook.api.dto;

import com.facebook.api.constant.RequestStatus;

public class RequestDTO {
    private String requesterId;
    private RequestStatus status;

    public RequestDTO() {
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
