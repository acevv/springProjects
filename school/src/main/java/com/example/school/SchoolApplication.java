package com.example.school;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.school.models.Role;
import com.example.school.models.User;
import com.example.school.service.UserService;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_STUDENT"));
//			userService.saveRole(new Role(null, "ROLE_PROFESSOR"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			
//			userService.saveUser(new User(null, "Aleksandar", "acev", "acev@gmail.com", "aleksandar1", new ArrayList<>()));
//			userService.saveUser(new User(null, "Nikola", "nikola", "nikola@gmail.com", "nikola123", new ArrayList<>()));
//			
//			userService.addRoleToUser("acev", "ROLE_ADMIN");
//			userService.addRoleToUser("nikola", "ROLE_USER");
//			userService.addRoleToUser("nikola", "ROLE_PROFESSOR");
//
//		};
//	}

}
