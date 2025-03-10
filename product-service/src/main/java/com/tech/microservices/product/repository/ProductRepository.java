package com.tech.microservices.product.repository;

import com.tech.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

// o banco de dados é o mongodb, por isso usa MongoRepository e o tipo do id é string
public interface ProductRepository extends MongoRepository<Product, String> {

}
