package com.fourTL.service;

import java.util.List;

import com.fourTL.entities.Products;

public interface ProductService {

	List<Products> findAll();

	Products findById(Integer id);
}
