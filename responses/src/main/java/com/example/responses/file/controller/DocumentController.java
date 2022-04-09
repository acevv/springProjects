package com.example.responses.file.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.responses.file.entity.Document;
import com.example.responses.file.repository.DocumentRepositroy;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
public class DocumentController {
	
	@Autowired
	DocumentRepositroy docRepo;
	
	@PostMapping("/upload")
	public ResponseEntity uploadToDb(@RequestParam("file") MultipartFile file, @RequestParam("fileName")String fileName) throws URISyntaxException {
		
		Document doc = new Document();
		doc.setFileName(fileName);
		try {
			doc.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Invalid file");
		}
		docRepo.save(doc);
		
		URI uri = new URI("http://localhost:8084/file/download/"+ fileName);
		return ResponseEntity.ok(uri);		
		
	}
	
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity downloadFile(@PathVariable("fileName") String fileName) {
		Document doc = docRepo.findByFileName(fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(doc.getFile());
	}

	
}
