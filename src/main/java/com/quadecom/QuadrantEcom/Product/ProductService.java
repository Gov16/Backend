package com.quadecom.QuadrantEcom.Product;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ApiResponse createProduct(Product product);

    ApiResponse getAllProducts();
}
