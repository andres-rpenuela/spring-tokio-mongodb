package org.tokio.spring.springmongodb.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/products/by-name",produces =  "application/json")
    public ResponseEntity<List<ProductDto>> getProductByNameHandler(@RequestParam(name = "name",defaultValue = StringUtils.EMPTY) String name){
        return ResponseEntity.ok(productService.findByName(name));
    }

    @PostMapping(value = "/products",consumes = "application/json", produces =  "application/json")
    public ResponseEntity<ProductDto> postProductsHandler(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.addProduct(productDto));
    }
}
