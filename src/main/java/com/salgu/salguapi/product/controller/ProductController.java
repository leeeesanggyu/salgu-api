package com.salgu.salguapi.product.controller;

import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/{productId}")
    public ResponseWithData getProduct(
            @PathVariable String productId
    ) {
        Product product = Product.builder()
                .id("aaaa-1111")
                .name("나이키 신발")
                .amount("199000")
                .build();
        return ResponseWithData.success(product);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String id;
        private String name;
        private String amount;
    }
}
