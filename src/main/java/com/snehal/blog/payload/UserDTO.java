package com.snehal.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
	
	private Integer id;
	@NotEmpty
	@Size(min=5,message = "Minimum 5 characters should be there.")
	private String name;
	@Email(message = "Email must be valid..!!")
	private String email;
	@NotEmpty
	@Size(min=3,max=10,message = "Password should contain min 3 charaters and maximum 10 characters.")
	private String password;
	@NotEmpty
	private String about;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
