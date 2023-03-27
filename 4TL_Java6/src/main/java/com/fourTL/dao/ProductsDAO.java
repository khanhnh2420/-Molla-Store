package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.Products;

public interface ProductsDAO extends JpaRepository<Products, Integer> {


}
