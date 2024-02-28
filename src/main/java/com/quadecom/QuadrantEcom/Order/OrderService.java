package com.quadecom.QuadrantEcom.Order;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.stereotype.Service;

public interface OrderService {
    ApiResponse createOrder(OrderRequestDto orderRequestDto);

    ApiResponse setPaymentFlag(String setPaymentFlag);

}
