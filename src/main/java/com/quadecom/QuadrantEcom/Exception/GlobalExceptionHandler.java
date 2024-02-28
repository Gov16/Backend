package com.quadecom.QuadrantEcom.Exception;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
        ApiResponse response = ex.getResponse();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
