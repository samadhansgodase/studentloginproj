package com.nt.congn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.service.StudentLoginService;


@Configuration
@EnableWebSecurity
public class ApplicationCogn {
	
	
	@Autowired
	private StudentLoginService service;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(service);
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return authenticationProvider;
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
	{
		return configuration.getAuthenticationManager();
	}
	
	
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		httpSecurity.authorizeHttpRequests((req->
		{
			req.requestMatchers("/api/save","/auth/login").permitAll()
			.anyRequest().authenticated();
			
			
		}));
		
		
		return httpSecurity.csrf(csrf->csrf.disable()).build();
	}

}
