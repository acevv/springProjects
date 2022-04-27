package com.example.tutorials_app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutorials_app.entity.Tutorials;
import com.example.tutorials_app.repository.TutorialsRepository;

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
			//tutorial.setPublished(false);
			tutorialRepo.save(tutorial);
			return new ResponseEntity<Tutorials>(tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorials>> getAll() {

		try {

			List<Tutorials> res = new ArrayList<>();
			res = tutorialRepo.findAll();

			if (res.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			Integer size = res.size();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Length", size.toString());
			return new ResponseEntity<>(res, HttpStatus.OK);
//			return new ResponseEntity<List<Tutorials>>(res, headers, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorials> getTutById(@PathVariable("id") Long id) {

		try {
			Tutorials res = tutorialRepo.findById(id).get();

			if (res != null)
				return new ResponseEntity<Tutorials>(res, HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorials> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorials nov) {
		try {
			Tutorials postoecki = tutorialRepo.findById(id).get();

			postoecki.setDescription(nov.getDescription());
			postoecki.setName(nov.getName());
			postoecki.setPublished(nov.getPublished());

			tutorialRepo.save(postoecki);

			return new ResponseEntity<Tutorials>(postoecki, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<?> deleteTutorialById(@PathVariable("id") Long id) {
		try {
			tutorialRepo.deleteById(id);
			return new ResponseEntity<>("Tutorial has been deleted", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<?> deleteAllTutorials() {
		try {
			tutorialRepo.deleteAll();
			return new ResponseEntity<>("All tutorials has been deleted", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/name")
	public ResponseEntity<List<Tutorials>> getAllByTitle(@RequestParam String keyword) {

		try {

			List<Tutorials> res = new ArrayList<>();

			res = tutorialRepo.findByNameContaining(keyword);

			if (res.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
