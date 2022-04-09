package com.example.student.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Major;
import com.example.student.entity.Student;
import com.example.student.entity.Subject;
import com.example.student.repository.MajorRepository;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.SubjectRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	MajorRepository majorRepo;

	@Autowired
	SubjectRepository subjectRepo;

	@GetMapping("/hello")
	public String hello() {
		return "Hello, world!";
	}

	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		
		Set<Subject> subjects = new HashSet<>();
		
		for (Subject subject : student.getMajor().getSubjects()) {
			
			subjects.add(subjectRepo.findByName(subject.getName()));
			
		}
		
		student.getMajor().setSubjects(subjects);
		majorRepo.save(student.getMajor());
		return studentRepo.save(student);
	}

	@GetMapping("/getAll")
	public List<Student> getAll() {
		return studentRepo.findAll();
	}

	@GetMapping("/getById/{id}")
	public Student getStudentById(@PathVariable("id") Long id) {

		return studentRepo.findById(id).get();
	}

	@GetMapping("/query")
	public Student getStudentByIndexQuery(@PathParam("index") String index) {
		return studentRepo.findByIndexQuery(index);
	}

	@GetMapping("")
	public List<Student> getStudentByIndex(@PathParam("index") String index) {
		return studentRepo.findByIndex(index);
	}

	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student newStudent) {

		Student oldStudent = studentRepo.findById(newStudent.getId()).get();

		oldStudent.setName(newStudent.getName());
		oldStudent.setSurname(newStudent.getSurname());
		oldStudent.setEmail(newStudent.getEmail());
		oldStudent.setIndex(newStudent.getIndex());

		Major oldMajor = majorRepo.findById(newStudent.getMajor().getId()).get();

		oldMajor.setGrade(newStudent.getMajor().getGrade());
		oldMajor.setName(newStudent.getMajor().getName());

		oldStudent.setMajor(oldMajor);

		studentRepo.save(oldStudent);

		return oldStudent;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") Long id) {

		studentRepo.deleteById(id);
		return "Deleted succsessfully!";

	}
}
