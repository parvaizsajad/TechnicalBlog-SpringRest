package com.techblog.serviceImpul;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techblog.entity.Category;
import com.techblog.exceptions.CategoryNotAvailableException;
import com.techblog.payload.CategoryDto;
import com.techblog.repository.CategoryRepositiry;
import com.techblog.service.CategoryService;
@Service
public class categoryServiceImpul implements CategoryService {
	
	@Autowired
	private CategoryRepositiry categoryRepositiry;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category category2 = this.categoryRepositiry.save(category);
		CategoryDto categorydto = this.modelMapper.map(category2, CategoryDto.class);
		return categorydto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList = this.categoryRepositiry.findAll();
		List<CategoryDto> categoryDtos = categoryList.stream().map(i-> this.modelMapper.map(i, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
		
	}

	@Override
	public CategoryDto findById(Integer id) {
		
		Category category = this.categoryRepositiry.findById(id).orElseThrow(()-> new CategoryNotAvailableException("Category",id));
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	
		CategoryDto categoryDto2 = this.findById(categoryId);
		categoryDto2.setCategoryTitle(categoryDto.getCategoryTitle());
		categoryDto2.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepositiry.save(this.modelMapper.map(categoryDto2, Category.class));
		return categoryDto2;
	}

	@Override
	public void deleteCategory(Integer id) {
		CategoryDto categoryDto = this.findById(id);
		Category category = this.modelMapper.map(categoryDto, Category.class);
		this.categoryRepositiry.delete(category);
		
		
	}

}
