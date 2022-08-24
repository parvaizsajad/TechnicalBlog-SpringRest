package com.techblog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techblog.exceptions.ApiResponse;
import com.techblog.payload.UserDto;
import com.techblog.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	
//post method to save the user in the database
	
@PostMapping("/save")
	public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto dto) {
	UserDto userDto = service.saveUser(dto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}

// Method to find the user using the id.
@GetMapping("/user/{id}")
public ResponseEntity<UserDto> findById(@PathVariable("id") int id) {
	
	
UserDto userDto = this.service.findById(id);
return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
}

// put method to edit the user

@PutMapping("/user/{id}")
public ResponseEntity<UserDto> findById(@RequestBody UserDto dto,@PathVariable("id") int id) {
   UserDto userdto=  this.service.updateUser(dto,id);
   return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
}


//Method to find all students
@GetMapping("/users")
public ResponseEntity<List<UserDto>> getAllUsers(){
	
	List<UserDto> findAllUsers = this.service.findAllUsers();
	
	return new ResponseEntity<List<UserDto>>(findAllUsers,HttpStatus.OK);
	
}
// Method to delete the User
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/user/{id}")
public  ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") int id){
	
	this.service.deleteUserById(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("The user wuth "+id+" is deleted",true),HttpStatus.OK);
}
}