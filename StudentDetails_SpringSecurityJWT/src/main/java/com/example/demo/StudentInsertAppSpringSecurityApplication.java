package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ns.repository.StudentRepository;

@SpringBootApplication
public class StudentInsertAppSpringSecurityApplication {
	@Autowired 
	public StudentRepository repository;

	/*
	 * public void initStudent() { List<Student> stud=Stream.of( new
	 * stud(id=101,firstname) );
	 * 
	 * }
	 */
	public static void main(String[] args) {
		SpringApplication.run(StudentInsertAppSpringSecurityApplication.class, args);
	}
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}

}
