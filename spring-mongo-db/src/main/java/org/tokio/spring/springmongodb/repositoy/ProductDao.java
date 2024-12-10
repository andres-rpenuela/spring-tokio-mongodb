package org.tokio.spring.springmongodb.repositoy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.tokio.spring.springmongodb.domain.Product;

import java.util.List;

@Repository
public interface ProductDao extends MongoRepository<Product, String> {

    // query method
    List<Product> findByName(String name);

    @Query("{ 'stock_quantity' :  { $gt:  ?0, $lt:  ?1 } }")
    List<Product> findBetweenStock(int lower, int higher);
}
