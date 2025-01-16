package com.shoppingapp.product_service.service;

import com.shoppingapp.product_service.dto.ProductRequest;
import com.shoppingapp.product_service.dto.ProductResponse;
import com.shoppingapp.product_service.model.Product;

import java.util.List;

public interface ProductService {
    public void createProduct(Product product);

    public List<ProductResponse> getAllProducts();
}
