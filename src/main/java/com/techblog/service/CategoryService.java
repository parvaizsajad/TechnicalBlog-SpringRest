package com.techblog.service;

import java.util.List;

import com.techblog.payload.CategoryDto;

public interface CategoryService {

	CategoryDto saveCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategories();

	CategoryDto findById(Integer id);
	
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	 
	 void deleteCategory(Integer id);
	 
	 
	 
	
	

}
