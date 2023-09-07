package com.snehal.blog.service;

import java.util.List;

import com.snehal.blog.payload.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer id);
	UserDTO getUserById(Integer id);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer id);
	

}
