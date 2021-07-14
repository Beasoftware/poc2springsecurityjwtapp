package com.ns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ns.model.Student;
import com.ns.service.RecordNotFoundException;
import com.ns.service.jwtStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	jwtStudentService service;

	@PostMapping("/AddStudent")
	public Student AddStudent(@RequestBody Student student) {
		return service.saveStudent(student);

	}

	@PostMapping("/AddStudents")
	public List<Student> AddStudents(@RequestBody List<Student> students) {
		return service.saveStudent(students);

	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = service.getAllStudents();

		return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Student entity = service.getStudentById(id);

		return new ResponseEntity<Student>(entity, new HttpHeaders(), HttpStatus.OK);
	}

}//class
