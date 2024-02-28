package com.quadecom.QuadrantEcom.Order;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            ApiResponse order  = orderService.createOrder(orderRequestDto);

            return ResponseEntity.ok(order);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse();
            response.setSuccess(false);
            response.setMessage("Failed to create order: " + ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/change-payment-status")
    public ResponseEntity<ApiResponse> setPaymentFlag(
            @RequestParam(required = false) String uid,
            @RequestParam(required = false) String successIndicator) {
        try {
            System.out.println(uid);
            System.out.println(successIndicator);
            ApiResponse order = new ApiResponse();

            if(uid!=null) {
                order = orderService.setPaymentFlag(uid);
            }else if (successIndicator!=null){
                order = orderService.setPaymentFlag(successIndicator);
            }

            return ResponseEntity.ok(order);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse();
            response.setSuccess(false);
            response.setMessage("Failed to create order: " + ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
