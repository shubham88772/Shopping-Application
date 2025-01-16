package com.shoppingapp.product_service.controller;

import com.shoppingapp.product_service.dto.ProductRequest;
import com.shoppingapp.product_service.dto.ProductResponse;
import com.shoppingapp.product_service.model.Product;
import com.shoppingapp.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createProduct(@RequestBody ProductRequest productRequest){
//        productService.createProduct(productRequest);
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> allProducts = productService.getAllProducts();
        return allProducts;

    }


}
