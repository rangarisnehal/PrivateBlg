package com.snehal.blog.payload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PostDTO {
	
	private Integer postId;
	private String postTitle;
	private String postContent;
	private String imageName;
	private CategoryDTO category;
	private UserDTO user;
	private List<CommentDTO> comment = new ArrayList<>();
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public List<CommentDTO> getComment() {
		return comment;
	}
	public void setComment(List<CommentDTO> comment) {
		this.comment = comment;
	}
	
	
}
