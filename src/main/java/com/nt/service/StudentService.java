package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Student;
import com.nt.repo.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo studentRepo;
	public String postData(Student student)
	{
		Student std=studentRepo.save(student);
		if(std==null)
		{
			return "Data Not Insrted";
		}
		return "Data Inserted Bro...";
	}

}
