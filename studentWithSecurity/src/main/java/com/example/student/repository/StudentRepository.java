package com.example.student.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Student;


@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{
	
	@Query(value = "SELECT * FROM student WHERE index =:indeks", nativeQuery = true)
	Student findByIndexQuery(@Param("indeks")String index);
	
	List<Student> findByIndex(String index);
	
	Page<Student> findById(Long id, Pageable pageable);
	

}
