package com.snehal.blog.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snehal.blog.entities.Category;
import com.snehal.blog.entities.Comment;
import com.snehal.blog.entities.Post;
import com.snehal.blog.entities.User;
import com.snehal.blog.exception.ResourceNotFoundException;
import com.snehal.blog.payload.CommentDTO;
import com.snehal.blog.payload.PostDTO;
import com.snehal.blog.payload.PostResponse;
import com.snehal.blog.payload.UserDTO;
import com.snehal.blog.repository.CategoryRepository;
import com.snehal.blog.repository.PostRepository;
import com.snehal.blog.repository.UserRepository;
import com.snehal.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User", " User id ", userId));
		
		Category category = this.catRepo.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " category id ", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", " post id ", postId));
		post.setTitle(postDto.getPostTitle());
		post.setContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		
		Post savedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", " post id ", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", " post id", postId));
		PostDTO postDtos = this.modelMapper.map(post, PostDTO.class);
		return postDtos;
	}

	@Override
	public PostResponse allPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));		
		Page<Post> element = this.postRepo.findAll(page);
		List<Post> posts = element.getContent();
		List<PostDTO> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(element.getNumber());
		postResponse.setPageSize(element.getSize());
		postResponse.setTotalElements(element.getTotalElements());
		postResponse.setTotalPages(element.getTotalPages());
		postResponse.setLastPage(element.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id ", userId));		
		List<Post> posts = this.postRepo.findByUser(user);		
		List<PostDTO> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());			
		return postDtos;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = this.catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " category id ", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDTO> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		/*
		 * This Method get list of posts by manual query written in repository
		 */
//		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDTO> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}
	
}
