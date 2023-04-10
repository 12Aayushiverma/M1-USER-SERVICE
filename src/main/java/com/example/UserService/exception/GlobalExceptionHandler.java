package com.example.UserService.exception;

import com.example.UserService.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourseNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
