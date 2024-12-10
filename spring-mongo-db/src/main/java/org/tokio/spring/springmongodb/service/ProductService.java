package org.tokio.spring.springmongodb.service;

import org.tokio.spring.springmongodb.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByName(String name);
    List<ProductDto> getAllProducts();
    List<ProductDto> findBetweenStock(int minStock, int maxStock);
    List<ProductDto> findByCategory(String category);

    ProductDto addProduct(ProductDto productDto);


}
