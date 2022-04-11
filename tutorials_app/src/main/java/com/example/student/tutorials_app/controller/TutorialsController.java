package com.example.student.tutorials_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.tutorials_app.entity.Tutorials;
import com.example.student.tutorials_app.repository.TutorialsRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialsController {

	@Autowired
	TutorialsRepository tutorialRepo;

	@PostMapping("/tutorials")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Tutorials> createTutorial(@RequestBody Tutorials tutorial) {
		try {

			tutorialRepo.save(tutorial);
			return new ResponseEntity<Tutorials>(tutorial, HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorials>> getAll() {

		List<Tutorials> result = new ArrayList<>();
		try {
			result = tutorialRepo.findAll();
			if (result.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			

//			Integer size = result.size();
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Length", "" + size.toString());

			return new ResponseEntity<>(result, HttpStatus.OK);
//			return new ResponseEntity<List<Tutorials>>(result, headers, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorials> getById(@PathVariable("id") Long id) {
		try {
			Tutorials res = tutorialRepo.findById(id).get();
			if (res != null) {
						return new ResponseEntity<Tutorials>(res, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<?> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorials nov) {

		try {
			Tutorials postoecki = tutorialRepo.getById(id);

			postoecki.setDescription(nov.getDescription());
			postoecki.setName(nov.getName());
			postoecki.setPublished(nov.getPublished());

			tutorialRepo.save(postoecki);

			return new ResponseEntity<>(postoecki, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<?> deleteTutorialById(@PathVariable("id") Long id) {
		try {
			tutorialRepo.deleteById(id);
			return new ResponseEntity<>("Tutorial has been deleted!", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<?> deleteAll() {
		try {
			tutorialRepo.deleteAll();
			return new ResponseEntity<>("All Tutorials have been deleted!", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("/tutorials")
//	public ResponseEntity<List<Tutorials>> getAllByTitle(@RequestParam String keyword) {
//		try {
//
//			List<Tutorials> res = new ArrayList<>();
//
//			res = tutorialRepo.findByNameContaining(keyword);
//
//			if (res.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(res, HttpStatus.OK);
//
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

}
