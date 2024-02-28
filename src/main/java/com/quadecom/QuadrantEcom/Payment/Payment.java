package com.quadecom.QuadrantEcom.Payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Text;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="payment_tbl")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;



    @Column(name = "account_identifier")
    private Integer accountIdentifier;



    @Column(name = "order_amt")
    private Number orderAmt;



    @Column(name = "order_date")
    private Date orderDate;


    @Column(name = "order_status")
    private String orderStatus;



    @Column(name = "order_id")
    private String orderId;



    @Column(name = "order_ref")
    private String orderRef;



    @Column(name = "payment_method")
    private String paymentMethod;




    @Column(name = "currency_type")
    private String currencyType;
}
