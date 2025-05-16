package com.example.similarproducts.client;

import com.example.similarproducts.exception.ProductNotFoundException;
import com.example.similarproducts.model.ProductDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ProductClient {

    private static final String BASE_URL = "http://localhost:3001";
    private static final String SIMILAR_IDS_PATH = "/product/{productId}/similarids";
    private static final String PRODUCT_DETAIL_PATH = "/product/{productId}";
    private static final int NOT_FOUND_STATUS = 404;

    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(BASE_URL).build();
    }

    public List<String> getSimilarProductIds(String productId) {
        return webClient.get()
                .uri(SIMILAR_IDS_PATH, productId)
                .retrieve()
                .onStatus(status -> status.value() == NOT_FOUND_STATUS, 
                          response -> Mono.error(new ProductNotFoundException(productId)))
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .block();
    }

    public ProductDetail getProductDetail(String productId) {
        return webClient.get()
                .uri(PRODUCT_DETAIL_PATH, productId)
                .retrieve()
                .onStatus(status -> status.value() == NOT_FOUND_STATUS, 
                          response -> Mono.error(new ProductNotFoundException(productId)))
                .bodyToMono(ProductDetail.class)
                .block();
    }
}
