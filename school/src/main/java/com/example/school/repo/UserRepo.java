package com.example.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
