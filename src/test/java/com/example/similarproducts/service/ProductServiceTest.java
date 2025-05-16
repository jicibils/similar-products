package com.example.similarproducts.service;

import com.example.similarproducts.client.ProductClient;
import com.example.similarproducts.model.ProductDetail;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void shouldReturnSimilarProducts() {
        ProductClient mockClient = mock(ProductClient.class);
        ProductService service = new ProductService(mockClient);

        when(mockClient.getSimilarProductIds("1")).thenReturn(Arrays.asList("2", "3"));
        when(mockClient.getProductDetail("2")).thenReturn(new ProductDetail("2", "Product 2", 20.0, true));
        when(mockClient.getProductDetail("3")).thenReturn(new ProductDetail("3", "Product 3", 30.0, true));

        List<ProductDetail> result = service.getSimilarProducts("1");

        assertEquals(2, result.size());
        assertEquals("2", result.get(0).getId());
    }
}
