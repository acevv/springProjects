	package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "SELECT * FROM student WHERE student.index=:ind", nativeQuery = true)
	Student findByIndexQuery(@Param("ind") String index);

	List<Student> findByIndex(@Param("index") String index);
}
