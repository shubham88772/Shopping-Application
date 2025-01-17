package com.shoppingapp.product_service.service;

import com.shoppingapp.product_service.dto.ProductRequest;
import com.shoppingapp.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();
}
