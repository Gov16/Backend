package com.quadecom.QuadrantEcom.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Text;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="product_tbl")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id_fk")
    private Integer categoryIdFk;

    @Column(name = "date")
    private String date;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "product_name")
    private String productName;
}
