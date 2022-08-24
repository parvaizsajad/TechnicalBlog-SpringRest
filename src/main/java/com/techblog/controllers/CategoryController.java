package com.techblog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techblog.exceptions.ApiResponse;
import com.techblog.payload.CategoryDto;
import com.techblog.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto saveCategory = this.categoryService.saveCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(saveCategory, HttpStatus.CREATED);

	}

	@GetMapping("/category")
	public ResponseEntity<?> getAllCategories() {

		List<CategoryDto> categoryList = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryList, HttpStatus.OK);

	}

	@GetMapping("/category/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Integer id) {

		CategoryDto category = this.categoryService.findById(id);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);

	}

	@PutMapping("/category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("id") Integer id) {

		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id) {

		this.categoryService.deleteCategory(id);

		return new ResponseEntity<ApiResponse>(new ApiResponse("The user wuth " + id + " is deleted", true),
				HttpStatus.OK);
	}
}
