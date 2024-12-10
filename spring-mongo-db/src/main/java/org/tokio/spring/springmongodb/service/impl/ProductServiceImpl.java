package org.tokio.spring.springmongodb.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tokio.spring.springmongodb.domain.Product;
import org.tokio.spring.springmongodb.dto.ProductDto;
import org.tokio.spring.springmongodb.repositoy.ProductDao;
import org.tokio.spring.springmongodb.service.ProductService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;
import java.util.stream.Collectors;

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
    public List<ProductDto> findByName(String name) {
        return Optional.ofNullable(name)
                .map(StringUtils::stripToNull) // Limpia el nombre de espacios en blanco o nulos
                .map(productDao::findByName) // Llama al DAO para obtener los productos
                .orElseGet(Collections::emptyList) // Si el nombre es nulo o no se encuentra nada, devuelve una lista vacía
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class)) // Convierte a ProductDto
                .collect(Collectors.toList());
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
    public List<ProductDto> findBetweenStock(int minStock, int maxStock) {
        return productDao.findBetweenStock(minStock,maxStock)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> findByCategory(String category) {
        return Optional.ofNullable(category)
                .map(StringUtils::stripToNull)
                .map(productDao::findByCategory)
                .orElseGet(Collections::emptyList)
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
