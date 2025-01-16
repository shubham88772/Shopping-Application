package com.shoppingapp.product_service.service.productserviceimpl;

import com.shoppingapp.product_service.dto.ProductRequest;
import com.shoppingapp.product_service.dto.ProductResponse;
import com.shoppingapp.product_service.model.Product;
import com.shoppingapp.product_service.repository.ProductRepository;
import com.shoppingapp.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
//    public void createProduct(ProductRequest productRequest) {
//        Product toProduct=modelMapper.map(productRequest,Product.class);
//        Product save = productRepository.save(toProduct);
////        log.info("Product is saved");
//    }
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> all = productRepository.findAll();
        List<ProductResponse> allProducts = all.stream().map(allProduct -> modelMapper.map(allProduct, ProductResponse.class)).collect(Collectors.toList());
        return allProducts;
    }
}
