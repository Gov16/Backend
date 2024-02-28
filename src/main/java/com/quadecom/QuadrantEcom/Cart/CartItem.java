package com.quadecom.QuadrantEcom.Cart;

import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMaster;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private NewCartEntity cart;

    @OneToOne
    @JoinColumn(name = "research_id")
    private ResearchMaster research;

    private Integer quantity;
    private Double totalPrice;
}

