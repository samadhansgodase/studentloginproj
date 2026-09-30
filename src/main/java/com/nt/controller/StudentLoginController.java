package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Student;

@RestController
@RequestMapping("/auth")
public class StudentLoginController {
	
	@Autowired
	
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/login")
	
	public ResponseEntity<String> getAccess(@RequestBody Student std)
	{
		try {
			UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(std.getEmail(),std.getPwd());
			Authentication autho=authenticationManager.authenticate(token);
			if(autho.isAuthenticated())
			{
				return new ResponseEntity<String>("User Login Succefuulyy",HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("UserName Or Password Is Incorect",HttpStatus.BAD_REQUEST);
		
	}

}
