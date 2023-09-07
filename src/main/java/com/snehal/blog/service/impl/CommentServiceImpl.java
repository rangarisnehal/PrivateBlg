package com.snehal.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snehal.blog.entities.Comment;
import com.snehal.blog.entities.Post;
import com.snehal.blog.exception.ResourceNotFoundException;
import com.snehal.blog.payload.CommentDTO;
import com.snehal.blog.payload.PostDTO;
import com.snehal.blog.repository.CommentRepository;
import com.snehal.blog.repository.PostRepository;
import com.snehal.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "post id ", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);		
		Comment savedComment = this.commentRepo.save(comment);		
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", " comment id ", commentId));
		this.commentRepo.delete(comment);

	}

}
