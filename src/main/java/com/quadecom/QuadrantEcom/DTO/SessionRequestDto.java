package com.quadecom.QuadrantEcom.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionRequestDto {
    private String apiOperation;
    private InteractionDTO interaction;
    private OrderDTO order;

    // Nested DTO classes for interaction and order
    @Data
    public static class InteractionDTO {
        private String operation;
        private String returnUrl;
        private MerchantDTO merchant;

    }

    @Data
    public static class MerchantDTO {
        private String name;
        private AddressDTO address;

    }

    @Data
    public static class AddressDTO {
        private String line1;
        private String line2;
    }

    @Data
    public static class OrderDTO {
        private String amount;
        private String currency;
        private String id;
        private String description;
        private String reference;
    }
}
