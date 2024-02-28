package com.quadecom.QuadrantEcom.Cart;

import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDto {
    private ResearchMasterDto research;
    private int quantity;
    private double totalPrice;
}
