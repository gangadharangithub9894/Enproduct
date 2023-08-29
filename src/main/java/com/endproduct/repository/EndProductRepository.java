package com.endproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endproduct.entity.EndProduct;

public interface EndProductRepository extends JpaRepository<EndProduct, Integer> {

}
