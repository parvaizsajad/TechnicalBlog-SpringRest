package com.techblog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techblog.entity.Role;
import com.techblog.helper.StaticConstants;
import com.techblog.repository.RoleRepo;

@SpringBootApplication
public class TechBlogApplication implements CommandLineRunner {

	@Autowired
	private RoleRepo Rolerepo;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(TechBlogApplication.class, args);
		
		
	
	}

	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {
		Role role1=new Role();
		role1.setId(StaticConstants.ADMIN_USER);
		role1.setName("ADMIN_USER");
		
		Role role2=new Role();
		role2.setId(StaticConstants.NORMAL_USER);
		role2.setName("NORMAL_USER");
		
		List<Role> roleList = List.of(role1,role2);
		this.Rolerepo.saveAll(roleList);
	}
	
	
}
