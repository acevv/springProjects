package com.example.tutorials_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tutorials_app.entity.Tutorials;

@Repository
public interface TutorialsRepository extends JpaRepository<Tutorials, Long>{
	
	List<Tutorials> findByNameContaining(String keyword);
	
}
