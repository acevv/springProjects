package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Major;
import com.example.student.entity.MajorDTO;

@Repository
public interface MajorDTORepo extends JpaRepository<Major, Long>{
	List<Major> findAll();
	
}
