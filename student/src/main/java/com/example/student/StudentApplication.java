package com.example.student;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.student.entity.Subject;
import com.example.student.enumration.SubjectName;
import com.example.student.repository.SubjectRepository;

@SpringBootApplication
public class StudentApplication {

	@Autowired
	SubjectRepository subjectRepo;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@PostConstruct
	public void initSubjects() {
		System.out.println("Initializing default subjects.");

		
		Subject s1 = new Subject(null, SubjectName.MATHEMATICS.name());
		subjectRepo.save(s1);

		Subject s2 = new Subject(null, SubjectName.ALGEBRA.name());
		subjectRepo.save(s2);

		Subject s3 = new Subject(null, SubjectName.ALGORITHMS.name());
		subjectRepo.save(s3);

		Subject s4 = new Subject(null, SubjectName.JAVA.name());
		subjectRepo.save(s4);

		Subject s5 = new Subject(null, SubjectName.PHYSICS.name());
		subjectRepo.save(s5);

	}

}
