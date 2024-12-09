package org.tokio.spring.springmongodb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tokio.spring.springmongodb.dto.ProductDto;
import org.tokio.spring.springmongodb.service.ProductService;

import java.util.List;

@RestController

public class ProductApiController {

    private final ProductService productService;

    @Autowired
    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products",produces =  "application/json")
    public ResponseEntity<List<ProductDto>> getProductsHandler(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping(value = "/products",consumes = "application/json", produces =  "application/json")
    public ResponseEntity<ProductDto> postProductsHandler(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.addProduct(productDto));
    }
}
