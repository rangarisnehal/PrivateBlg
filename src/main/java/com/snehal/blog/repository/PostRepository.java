package com.snehal.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snehal.blog.entities.Category;
import com.snehal.blog.entities.Post;
import com.snehal.blog.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	
	public List<Post> findByTitleContaining(String title);
	
	/*
	 * This is the Example of manual query 
	 */
//	@Query("select p from Post p where p.title like :key")
//	public List<Post> searchByTitle(@Param("key") String title);
}
