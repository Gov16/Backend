package com.quadecom.QuadrantEcom.ServiceImpl;

import com.quadecom.QuadrantEcom.Order.OrderRepository;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.HDFCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HDFCServiceImpl implements HDFCServices {

    @Autowired
    OrderRepository orderRepo;

    @Override
    public ApiResponse findOrdersWithUserDetailsBySuccessIndicator(String successIndicator) {

        List<Object[]> orderData = orderRepo.findOrdersWithUserDetailsBySuccessIndicator(successIndicator);
        ApiResponse response = new ApiResponse();

        if(!orderData.isEmpty()){
            System.out.println(orderData);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatusCode.valueOf(HttpStatus.OK.value()));
            response.setMessage("Payment was captured and is being processed !");
            response.setPaymentStatus(true);
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
            response.setMessage("Payment was declined or is being confirmed ! Please wait for 24 HRS for our team to confirm ang get back to you ! You can Contact us on support@quadrant-solutions.com with your Email and mobile number the you used to buy this product ! Thank you for choosing Quadrant Knowledge Solutions !");
            response.setPaymentStatus(false);
            System.out.println("No Data Found !");
        }

        return response;
    }
}
