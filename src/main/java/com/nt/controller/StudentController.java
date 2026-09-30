package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.congn.ApplicationCogn;
import com.nt.entity.Student;
import com.nt.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private final StudentService service;
	
	StudentController(StudentService service, ApplicationCogn applicationCogn) {
		this.service = service;
		this.applicationCogn = applicationCogn;
		
	}
	
	private final  ApplicationCogn applicationCogn;
	
	
	
	
	
	@PostMapping("/save")
	public ResponseEntity<String> postData(@RequestBody Student std) {
		
		String pWDString=std.getPwd();
		String p4=applicationCogn.bCryptPasswordEncoder().encode(pWDString);
		std.setPwd(p4);

	   String student=service.postData(std);

	    if (student != null) {
	        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	    }

	    return new ResponseEntity<>("Data  Not saved", HttpStatus.BAD_REQUEST);
	}

}
