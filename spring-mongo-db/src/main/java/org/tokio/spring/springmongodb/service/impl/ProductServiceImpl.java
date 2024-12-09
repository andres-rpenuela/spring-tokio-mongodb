package org.tokio.spring.springmongodb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tokio.spring.springmongodb.domain.Product;
import org.tokio.spring.springmongodb.dto.ProductDto;
import org.tokio.spring.springmongodb.repositoy.ProductDao;
import org.tokio.spring.springmongodb.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ModelMapper modelMapper) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.findAll();
        return products
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = new Product();
        populationCreateOrUpdate(product,productDto);
        productDto.setId(product.getId());
        return productDto;
    }

    private void populationCreateOrUpdate(Product product, ProductDto productDto) {
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setTaxes(productDto.getTaxes());
        product.setCategory(productDto.getCategory());
        if(product.getCreatedAt() == null){
            product.setCreatedAt(LocalDateTime.now());
        }
        product.setUpdatedAt(LocalDateTime.now());
        productDao.save(product);
    }


}
