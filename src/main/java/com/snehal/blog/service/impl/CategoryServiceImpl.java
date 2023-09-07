package com.snehal.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snehal.blog.entities.Category;
import com.snehal.blog.exception.ResourceNotFoundException;
import com.snehal.blog.payload.CategoryDTO;
import com.snehal.blog.repository.CategoryRepository;
import com.snehal.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(saveedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " Category Id ", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepository.save(category);
		
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " Category Id ", categoryId));
		this.categoryRepository.delete(category);
		
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " Category Id ", categoryId));
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> allCategory() {
		List<Category> category = this.categoryRepository.findAll();
		List<CategoryDTO> categoryDtos = category.stream().map(cat->this.modelMapper.map(cat,CategoryDTO.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
