package com.quadecom.QuadrantEcom.DTO;

import com.quadecom.QuadrantEcom.Order.OrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SessionCreationDTO {

    private SessionRequestDto sessionRequestDto;
    private OrderRequestDto orderRequestDto;
}
