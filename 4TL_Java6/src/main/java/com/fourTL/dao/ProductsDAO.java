package com.fourTL.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fourTL.entities.Products;

@Repository
public interface ProductsDAO extends JpaRepository<Products, Integer> {

	// Tìm sản phẩm theo CategoryID
	List<Products> findByCategoryId(String categoryId);

	// Tìm sản phẩm theo search
	List<Products> findByNameContaining(String search);

	long countByCategoryId(String categoryId);

	// Tìm sản phẩm theo Categories
	@Query("SELECT p FROM Products p WHERE p.category.id = ?1")
	Page<Products> findByCategoryId(String categoryId, Pageable pageable);

	// Tìm sản phẩm theo Source
	@Query("SELECT p FROM Products p WHERE p.source = ?1")
	Page<Products> findBySource(String source, Pageable pageable);
}
