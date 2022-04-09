package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.MajorDTO;
import com.example.student.repository.MajorRepository;


@RestController
@RequestMapping("/major")
public class MajorController {

	@Autowired
	MajorRepository majorDTO;
	
	@GetMapping
	List<MajorDTO> getAll() {
		
		return majorDTO.findAllByName();
		
		
	}
	
}
