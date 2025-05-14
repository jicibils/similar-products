package com.example.similarproducts.controller;

import com.example.similarproducts.model.ProductDetail;
import com.example.similarproducts.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getSimilarProducts(@PathVariable String productId) {
        List<ProductDetail> products = productService.getSimilarProducts(productId);
        return ResponseEntity.ok(products);
    }
}