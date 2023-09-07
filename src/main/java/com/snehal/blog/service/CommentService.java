package com.snehal.blog.service;

import com.snehal.blog.payload.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDto, Integer postId);
	
	void deleteComment(Integer commentId);

}
