package com.snehal.blog.service;

import java.util.List;

import com.snehal.blog.entities.Post;
import com.snehal.blog.payload.PostDTO;
import com.snehal.blog.payload.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
	
	PostDTO updatePost(PostDTO postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDTO getPostById(Integer postId);
	
	PostResponse allPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	List<PostDTO> getPostByUser(Integer userId);
	
	List<PostDTO> getPostByCategory(Integer categoryId); 
	
	List<PostDTO> searchPost(String keyword);

}
