package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepository studentRepo;

	@PostMapping("/create")
	public String createStudent(@RequestBody Student student) {
		studentRepo.save(student);
		
		return "Student with ID: " + student.getId() + " is created.";
	}
	
	@GetMapping("/getAll")
	public List<Student> getAll() {
		return studentRepo.findAll();
	}

}
