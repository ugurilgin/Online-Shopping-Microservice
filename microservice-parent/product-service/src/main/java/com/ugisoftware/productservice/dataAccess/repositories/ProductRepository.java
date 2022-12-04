package com.ugisoftware.productservice.dataAccess.repositories;

import com.ugisoftware.productservice.entities.concretes.Products;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Products,String> {
}
