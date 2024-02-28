package com.quadecom.QuadrantEcom.Services;


import com.quadecom.QuadrantEcom.Response.ApiResponse;

import java.util.List;

public interface HDFCServices {
    ApiResponse findOrdersWithUserDetailsBySuccessIndicator(String successIndicator);
}
