package com.snehal.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.snehal.blog.config.AppConstant;
import com.snehal.blog.payload.PostDTO;
import com.snehal.blog.payload.PostResponse;
import com.snehal.blog.service.FileService;
import com.snehal.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create post	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDTO> createPosts(
			@RequestBody PostDTO postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDTO createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDTO>(createdPost,HttpStatus.CREATED);
	}
	
	
	// find posts by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDTO> postDtos = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postDtos,HttpStatus.OK);
	}
	
	//find posts by category
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDTO>> gesPostsBycategory(@PathVariable Integer categoryId){
		List<PostDTO> postDtos = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(postDtos,HttpStatus.OK);
	}
	
	//find post by post Id
	@GetMapping("/post/{postId}")	
	public ResponseEntity<PostDTO> getSinglePost(@PathVariable Integer postId){
		PostDTO postDtos = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDtos,HttpStatus.OK);
	}
	
	
	// Find all posts
	@GetMapping("/post")
	public ResponseEntity<PostResponse> findAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy){
		 PostResponse allPosts = this.postService.allPost(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
	}
	
	// Delete post
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId){		
		this.postService.deletePost(postId);
		return new ResponseEntity<String>("Succefully Deleted..!!",HttpStatus.OK);
	}
	
	// Update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto,@PathVariable Integer postId){
		PostDTO updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
	}
	
	// Search post
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String keyword){
		List<PostDTO> posts = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	
	// Upload a post image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException{
		PostDTO postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadFile(path, image);		
		postDto.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
		
	}
}
