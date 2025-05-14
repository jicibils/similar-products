package com.example.similarproducts.service;

import com.example.similarproducts.client.ProductClient;
import com.example.similarproducts.exception.ProductNotFoundException;
import com.example.similarproducts.model.ProductDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<ProductDetail> getSimilarProducts(String productId) {
        List<String> similarIds = productClient.getSimilarProductIds(productId);
    
        return similarIds.stream()
            .map(id -> {
                try {
                    return productClient.getProductDetail(id);
                } catch (ProductNotFoundException e) {
                    System.err.println("PRODUCT NOT FOUND " + id);
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}