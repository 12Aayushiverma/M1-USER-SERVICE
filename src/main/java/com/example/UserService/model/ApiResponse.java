package com.example.UserService.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.http.HttpStatus;

public class ApiResponse {

    private String message;

    private Boolean success;

    private HttpStatus  status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ApiResponse(String message, Boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ApiResponse() {
    }



}
