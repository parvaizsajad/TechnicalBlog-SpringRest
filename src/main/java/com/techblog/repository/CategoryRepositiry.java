package com.techblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techblog.entity.Category;

public interface CategoryRepositiry extends JpaRepository<Category, Integer> {
	

}
