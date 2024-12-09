package org.tokio.spring.springmongodb.service;

import org.tokio.spring.springmongodb.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto addProduct(ProductDto productDto);
}
