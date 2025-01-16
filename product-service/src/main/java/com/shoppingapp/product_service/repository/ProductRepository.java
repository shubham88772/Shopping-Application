package com.shoppingapp.product_service.repository;

import com.shoppingapp.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
