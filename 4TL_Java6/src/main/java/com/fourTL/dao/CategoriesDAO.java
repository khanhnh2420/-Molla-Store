package com.fourTL.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourTL.entities.Categories;


public interface CategoriesDAO extends JpaRepository<Categories, String> {
    
}
