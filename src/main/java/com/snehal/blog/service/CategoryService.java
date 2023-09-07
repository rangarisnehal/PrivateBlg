package com.snehal.blog.service;

import java.util.List;

import com.snehal.blog.payload.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDto);
	CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);
	void deleteCategory(Integer categoryId);
	CategoryDTO getCategoryById(Integer categoryId);
	List<CategoryDTO> allCategory();

}
