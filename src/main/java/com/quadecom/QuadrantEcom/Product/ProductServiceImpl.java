package com.quadecom.QuadrantEcom.Product;

import com.quadecom.QuadrantEcom.Exception.ApiException;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse createProduct(Product product) {
        ApiResponse response = new ApiResponse();

        // Check if the product name already exists
        if (productRepository.existsByProductName(product.getProductName())) {
            response.setSuccess(false);
            response.setMessage("Product name already exists");
            throw new ApiException(response);
        }

        // Save the product entity
        try {
            productRepository.save(product);
            response.setSuccess(true);
            response.setMessage("User Saved Successfully");
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public ApiResponse getAllProducts() {
        ApiResponse response = new ApiResponse();
        try {
            List<Product> prodList = productRepository.findAllProducts();
            if (prodList.isEmpty()){
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setSuccess(false);
                response.setMessage("Cannot Find Products Please Check Logs.");
            }
            response.setProduct(prodList);
            response.setStatus(HttpStatus.OK);
            response.setSuccess(true);
            response.setMessage("Fetched All Products");
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

}
