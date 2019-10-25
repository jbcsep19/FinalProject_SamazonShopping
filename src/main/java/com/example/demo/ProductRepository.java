package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findByNameContainingIgnoreCase(String name);
    ArrayList<Product> findByDescriptionContainingIgnoreCase(String details);
}
