package com.example.student;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.student.entity.Subject;
import com.example.student.enumeration.SubjectName;
import com.example.student.repository.SubjectRepository;

@SpringBootApplication
public class StudentApplication {

	@Autowired
	SubjectRepository subjectRepo;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@PostConstruct
	public void initSubject() {
		System.out.println("Initializing default subjects");

		if (subjectRepo.findByName(SubjectName.ALGEBRA.name()) == null) {
			Subject s1 = new Subject(null, SubjectName.ALGEBRA.name());
			subjectRepo.save(s1);
		}
		if (subjectRepo.findByName(SubjectName.ALGORITHMS.name()) == null) {
			Subject s2 = new Subject(null, SubjectName.ALGORITHMS.name());
			subjectRepo.save(s2);
		}
		if (subjectRepo.findByName(SubjectName.JAVA.name()) == null) {
			Subject s3 = new Subject(null, SubjectName.JAVA.name());
			subjectRepo.save(s3);
		}
		if (subjectRepo.findByName(SubjectName.MATHEMATIKA.name()) == null) {
			Subject s4 = new Subject(null, SubjectName.MATHEMATIKA.name());
			subjectRepo.save(s4);
		}
		if (subjectRepo.findByName(SubjectName.PHISICS.name()) == null) {
			Subject s5 = new Subject(null, SubjectName.PHISICS.name());
			subjectRepo.save(s5);
		}
	}

}
