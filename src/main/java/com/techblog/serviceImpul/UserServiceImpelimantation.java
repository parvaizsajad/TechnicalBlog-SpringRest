package com.techblog.serviceImpul;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techblog.entity.Role;
import com.techblog.entity.User;
import com.techblog.exceptions.ResourceNotFoundException;
import com.techblog.helper.StaticConstants;
import com.techblog.payload.UserDto;
import com.techblog.repository.RoleRepo;
import com.techblog.repository.UserRepository;
import com.techblog.service.UserService;
@Service
public class UserServiceImpelimantation implements UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo Rolerepo;
	
	

	@Override
	public UserDto saveUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	Role role = this.Rolerepo.findById(StaticConstants.NORMAL_USER).get();
	user.getRoles().add(role);
		
User save = repository.save(user);
	UserDto userDto2 = this.modelMapper.map(save, UserDto.class);
		return userDto2;
	}
	
	

	@Override
	public UserDto findById(int id) {
		
	User user = this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "user", id));
   UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto user, int id) {
		
	
	
		 User rep = this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException(user.getName(), user.getName(), id));
		rep.setName(user.getName());
		rep.setName(user.getName());
		rep.setEmail(user.getEmail());
		rep.setPassword(user.getPassword());
		rep.setAbout(user.getAbout());
		
		User saveuser = this.repository.save(rep);
		UserDto user_to_userDto = this.modelMapper.map(saveuser, UserDto.class);
	
		return user_to_userDto;
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<User> list = this.repository.findAll();
	List<UserDto> list2 = list.stream().map(i-> this.modelMapper.map(i, UserDto.class)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public void deleteUserById(int id) {
		 User rep = this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","User", id));
		 
	this.repository.delete(rep);
		
	}


}
