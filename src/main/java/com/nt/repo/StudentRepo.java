package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Student;
import java.util.List;


public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	public Student  findByEmail(String email);

}
