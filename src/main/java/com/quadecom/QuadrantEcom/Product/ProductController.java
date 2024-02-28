package com.quadecom.QuadrantEcom.Product;

import com.quadecom.QuadrantEcom.Exception.ApiException;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        try {
            productService.createProduct(product);
            ApiResponse response = new ApiResponse();
            response.setSuccess(true);
            response.setMessage("Product created successfully");
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            ApiResponse products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
