package com.techblog.service;

import java.util.List;

import com.techblog.payload.UserDto;


public interface UserService {
	
	UserDto saveUser(UserDto user);

	UserDto findById(int id);

	UserDto updateUser(UserDto dto, int id);

	List<UserDto> findAllUsers();

	void deleteUserById(int id);

}
