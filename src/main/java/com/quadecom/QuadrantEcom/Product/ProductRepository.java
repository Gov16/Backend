package com.quadecom.QuadrantEcom.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String name);

    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

}
