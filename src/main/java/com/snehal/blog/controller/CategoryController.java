package com.snehal.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snehal.blog.payload.CategoryDTO;
import com.snehal.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//Create category
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto){
		CategoryDTO savedCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDTO>(savedCategory,HttpStatus.CREATED);
	}
	
	//Update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto, @PathVariable Integer categoryId){
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.OK);
	}
	
	//delete Category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>("Succefully Deleted..",HttpStatus.OK);
	}
	
	//get single category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable Integer categoryId){
		CategoryDTO category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDTO>(category,HttpStatus.OK);
	}
	
	// get all categories	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> allCategories(){
		List<CategoryDTO> allCategories = this.categoryService.allCategory();
		return new ResponseEntity<List<CategoryDTO>>(allCategories,HttpStatus.OK);
	}

}
