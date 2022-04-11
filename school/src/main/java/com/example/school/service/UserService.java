package com.example.school.service;

import java.util.List;

import com.example.school.models.Role;
import com.example.school.models.User;

public interface UserService {
	
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();

}
