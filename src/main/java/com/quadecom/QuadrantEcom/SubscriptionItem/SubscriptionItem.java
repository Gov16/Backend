package com.quadecom.QuadrantEcom.SubscriptionItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="subscription_item_tbl")
public class SubscriptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "subscription_id_fk")
    private Integer subscriptionIdFk;



    @Column(name = "product_id_fk")
    private Integer productIdFk;



    @Column(name = "quantity")
    private Integer quantity;



    @Column(name = "udated_at")
    private Date updatedAt;



    @Column(name = "created_at")
    private Date createdAt;



    @Column(name = "status")
    private Integer status;

}
