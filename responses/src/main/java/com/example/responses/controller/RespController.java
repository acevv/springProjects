package com.example.responses.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responses.JobThread;

@RestController
@RequestMapping("/responses")
@CrossOrigin
public class RespController {

	@Autowired
	JobThread thread;
	
	@GetMapping("/hello")
	ResponseEntity<String> hello() {
		
//		JobThread thread = new JobThread();
		thread.getState();
		thread.start();
		
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@GetMapping("/age")
	ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {

		if (isInFuture(yearOfBirth)) {
			return new ResponseEntity<>("Year of birth cannot be in the future", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Your age is " + calculateAge(yearOfBirth), HttpStatus.OK);
	}
	
	@GetMapping("/customHeader")
    public ResponseEntity<String> customHeader2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        headers.add("Validen odgovor", "tochen");

        return new ResponseEntity<>("Custom header set", headers, HttpStatus.OK);
    }

	@GetMapping("/builder/hello")
	ResponseEntity<String> hello2() {
		return ResponseEntity.ok("Hello World!");
	}

	@GetMapping("/builder/age")
	ResponseEntity<String> age2(@RequestParam("yearOfBirth") int yearOfBirth) {
		List<String> years = new ArrayList<>();
		years.add("2021");
		years.add("2020");
		
		if (isInFuture(yearOfBirth)) {
			return ResponseEntity.badRequest().body("Year of birth cannot be in the future, you need to choose from: " + years );
		}

		return ResponseEntity.status(HttpStatus.OK).body("Your age is " + calculateAge(yearOfBirth));
	}

	@GetMapping("/builder/customHeader")
	ResponseEntity<String> customHeader() {
		return ResponseEntity.ok().header("Custom-Header", "foo").body("Custom header set");
	}
	


	private int calculateAge(int yearOfBirth) {
		return currentYear() - yearOfBirth;
	}

	private boolean isInFuture(int year) {
		return currentYear() < year;
	}

	private int currentYear() {
		return Year.now().getValue();
	}

}
