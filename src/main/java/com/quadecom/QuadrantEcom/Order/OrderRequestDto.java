package com.quadecom.QuadrantEcom.Order;

import com.quadecom.QuadrantEcom.Cart.CartItemRequestDto;
import com.quadecom.QuadrantEcom.Users.UserRequestDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private List<CartItemRequestDto> cart;
    private UserRequestDto user;
    private OrderDataDto order;

}
