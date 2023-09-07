package com.snehal.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snehal.blog.payload.UserDTO;
import com.snehal.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Create user
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		UserDTO createdUser = userService.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	//Update user
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable("id") Integer id){
		UserDTO updatedUser = userService.updateUser(userDto, id);
		return new ResponseEntity<UserDTO>(updatedUser,HttpStatus.OK);
	}
	
	//Delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<String>("Succefully deleted..!!",HttpStatus.OK);
	}
	
	//Get Single user
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer id){
		UserDTO user = userService.getUserById(id);
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
	
	// Get list of user
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> allUsers(){
		List<UserDTO> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(allUsers,HttpStatus.OK);
	}

}
