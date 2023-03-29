package com.fourTL.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourTL.dao.ProductsDAO;
import com.fourTL.entities.Products;
import com.fourTL.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductsDAO pDAO;

	@Override
	public List<Products> findAll() {
		return pDAO.findAll();
	}

	@Override
	public Products findById(Integer id) {
		return pDAO.findById(id).get();
	}
	
}
