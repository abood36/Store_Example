package com.store_example.store_example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store_example.store_example.models.product;

@Repository
public interface productRepo extends JpaRepository<product, Integer>{
    
}
