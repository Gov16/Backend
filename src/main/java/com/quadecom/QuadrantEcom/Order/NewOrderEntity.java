package com.quadecom.QuadrantEcom.Order;

import com.quadecom.QuadrantEcom.Cart.NewCartEntity;
import com.quadecom.QuadrantEcom.Users.UpdatedUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="new_order")
public class NewOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private UpdatedUserEntity user;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "session_id")
    private String sessionID;

    @Column(name = "success_indicator")
    private String successIndicator;

    @Column(name = "total_amount")
    private String totalAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "payment_status")
    private Boolean paymentStatus;

    @OneToMany(mappedBy = "order")
    private List<NewCartEntity> carts;

    @Column(name = "payment_type")
    private String paymentType;

}
