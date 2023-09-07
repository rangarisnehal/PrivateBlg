package com.snehal.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snehal.blog.payload.CommentDTO;
import com.snehal.blog.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	// create 
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDTO> createCommment(@RequestBody CommentDTO commentDto,@PathVariable Integer postId)
	{
		CommentDTO comment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDTO>(comment,HttpStatus.OK);
	}
}
