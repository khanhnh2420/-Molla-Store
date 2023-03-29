package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourTL.entities.Categories;

public interface CategoriesDAO extends JpaRepository<Categories, String>{
	
}
