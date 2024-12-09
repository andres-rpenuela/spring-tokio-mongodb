package org.tokio.spring.springmongodb.repositoy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tokio.spring.springmongodb.domain.Product;

@Repository
public interface ProductDao extends MongoRepository<Product, String> {
}
