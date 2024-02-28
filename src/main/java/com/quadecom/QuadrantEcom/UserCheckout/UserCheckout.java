package com.quadecom.QuadrantEcom.UserCheckout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="user_checkout_init")
public class UserCheckout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mobile")
    private Integer mobile;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "reference")
    private String reference;

    @Column(name = "currency")
    private String currency;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "session_id")
    private String sessionId;

}
