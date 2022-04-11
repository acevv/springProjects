package com.example.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
