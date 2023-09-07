package com.snehal.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snehal.blog.entities.User;
import com.snehal.blog.exception.ResourceNotFoundException;
import com.snehal.blog.payload.UserDTO;
import com.snehal.blog.repository.UserRepository;
import com.snehal.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer id) {
		User user = userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", " id ", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);	
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", " id ", id));

		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		 List<User> users = userRepo.findAll();				
		 List<UserDTO> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		 
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", " Id ", id));
		userRepo.delete(user);
	}
	
	
	public User dtoToUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDTO userToDto(User user) {
		UserDTO userDto =this.modelMapper.map(user, UserDTO.class);
				
		return userDto;
	}

}
