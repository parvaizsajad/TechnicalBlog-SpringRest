package com.techblog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.techblog.entity.Role;

import lombok.Data;
@Data
public class UserDto {

	
	
	private int id;
	@NotEmpty(message = "user name can not be empty")
	@Size(min = 4,max = 50,message = "please enter the valid size")
	
	private String name;
	@NotEmpty(message = "email can not be empty")
	@Email(message = "enter the valid email")
	@Size(min = 4,max = 50,message = "please enter the valid size")
	private String email;
	@NotEmpty(message = "password can not be empty")
	@Size(min = 4,max = 50,message = "please enter the valid size")
	private String password;
	private String about;
	private Set<Role> roles = new HashSet<>();
	
	
}
