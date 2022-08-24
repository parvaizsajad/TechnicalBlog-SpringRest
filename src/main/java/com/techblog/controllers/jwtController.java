package com.techblog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techblog.exceptions.ApiResponse;
import com.techblog.payload.UserRequest;
import com.techblog.payload.UserResponse;
import com.techblog.security.CustomUserDetailsService;
import com.techblog.security.JwtTokenUtil;

@RestController
public class jwtController {
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> GenerateToken(@RequestBody UserRequest request) throws Exception{
		
		try {
	
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Bad Credentials, Please check your credintials");
		}
		
		UserDetails userdetails = this.customUserDetailService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenUtil.generateToken(userdetails);
		
		return new ResponseEntity<UserResponse>(new UserResponse(token),HttpStatus.OK);

		
		
	}

}