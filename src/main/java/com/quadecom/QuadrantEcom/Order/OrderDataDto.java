package com.quadecom.QuadrantEcom.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataDto {

    private String sessionID;
    private String successIndicator;
    private String totalAmount;
    private String currency;
    private String orderId;
    private String amount;
    private String id;
    private String description;
    private String reference;
}
