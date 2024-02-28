package com.quadecom.QuadrantEcom.Order;

import com.quadecom.QuadrantEcom.Cart.*;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMaster;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterRepository;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Users.UpdatedUserEntity;
import com.quadecom.QuadrantEcom.Users.UserRepository;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.ValidationAnnotationUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ResearchMasterRepository researchRepository;

    @Override
    public ApiResponse setPaymentFlag(String successIndicator){
        ApiResponse response = new ApiResponse();

        NewOrderEntity orderData = orderRepository.findBySuccessIndicator(successIndicator);

        try {

            if(orderData != null){
                orderData.setPaymentStatus(true);
                orderData.setPaymentType("HDFC");
                orderRepository.save(orderData);
                response.setStatus(HttpStatus.OK);
                response.setPaymentStatus(true);
                response.setSuccess(true);
            }
        }catch (Exception e){
            orderData.setPaymentStatus(false);
            orderRepository.save(orderData);
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setPaymentStatus(false);
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public ApiResponse createOrder(OrderRequestDto orderRequestDto) {
        // Create User entity
        UpdatedUserEntity user = new UpdatedUserEntity();
        user.setFName(orderRequestDto.getUser().getName());
        user.setPhone(orderRequestDto.getUser().getMobileNumber());
        user.setEmail(orderRequestDto.getUser().getWorkEmail());

        userRepository.save(user);

        NewOrderEntity order = new NewOrderEntity();

        if( orderRequestDto.getOrder() != null ) {
            // Create Order entity

            order.setOrderDate(new Date());
            order.setUser(user);
            order.setOrderId(orderRequestDto.getOrder().getOrderId());
            order.setSessionID(orderRequestDto.getOrder().getSessionID());
            order.setSuccessIndicator(orderRequestDto.getOrder().getSuccessIndicator());
            order.setTotalAmount(orderRequestDto.getOrder().getTotalAmount());
            order.setCurrency(orderRequestDto.getOrder().getCurrency());

            if(orderRequestDto.getOrder().getCurrency() == "USD"){
                order.setPaymentType("paypal");
            }

            orderRepository.save(order);
        }else{
            order.setPaymentType("paypal");
            orderRepository.save(order);
        }

        // Create Cart entity
        NewCartEntity cart = new NewCartEntity();
        cart.setOrder(order);

        cartRepository.save(cart);

        List<CartItem> cartItems = new ArrayList<>();
        // Iterate over cart items
        for (CartItemRequestDto cartItemRequestDto : orderRequestDto.getCart()) {
            // Create Research entity
            ResearchMaster research = researchRepository.findById(cartItemRequestDto.getResearch().getId())
                    .orElseThrow(() -> new RuntimeException("Research with ID " + cartItemRequestDto.getResearch().getId() + " not found"));

            // Create CartItem entity
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setResearch(research);
            cartItem.setQuantity(cartItemRequestDto.getQuantity());
            cartItem.setTotalPrice(cartItemRequestDto.getTotalPrice());

            cartItemRepository.save(cartItem);
        }

        // Set the created OrderEntity in the ApiResponse
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Order created successfully.");
        response.setStatus(HttpStatus.OK);
        response.setOrder(order); // Set the created OrderEntity
        response.setOrderData(orderRequestDto);
//        response.setCartItems(cartItems);
        // Return the response or use it according to your needs

        return response;
    }
}
