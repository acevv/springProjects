package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Subject findByName(String name);
}
