package com.inventory.pruebatecnica.repository;

import com.inventory.pruebatecnica.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {

}
