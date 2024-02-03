package com.springapp.sklep.repositories;

import com.springapp.sklep.domain.Product;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends CrudRepository<Product, Long> {
}
