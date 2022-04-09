package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.entity.MajorDTO;

@Repository
public interface MajorDTORepo extends JpaRepository<MajorDTO, Long>{
	
	

}
