package com.quadecom.QuadrantEcom.Exception;

import com.quadecom.QuadrantEcom.Response.ApiResponse;

public class ApiException extends RuntimeException{

    private ApiResponse response;

    public ApiException(ApiResponse response) {
        this.response = response;
    }

    public ApiResponse getResponse() {
        return response;
    }

}
