package com.fourTL.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourTL.dao.CategoriesDAO;
import com.fourTL.entities.Categories;
import com.fourTL.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoriesDAO cDAO;
	
	@Override
	public List<Categories> findAll() {
		return cDAO.findAll();
	}

}
