package com.ns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ns.model.Student;
import com.ns.repository.StudentRepository;
@Service
public class jwtStudentService {
	@Autowired
	StudentRepository repository;
	
	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException {
	 * 
	 * DAOStudent student = studentDao.findByUsername(username); if (user == null) {
	 * throw new UsernameNotFoundException("User not found with username: " +
	 * username); } return new
	 * org.springframework.security.core.userdetails.User(user.getUsername(),
	 * user.getPassword(), new ArrayList<>());
	 */

	public List<Student> getAllStudents() {
		List<Student> studentList = repository.findAll();

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}
	}

	public Student getStudentById(Long id) throws RecordNotFoundException {
		Optional<Student> student = repository.findById(id);

		if (student.isPresent()) {
			return student.get();
		} else {
			throw new RecordNotFoundException("No student record exist for given id", id);
		}
	}

	public List<Student> saveStudent(List<Student> students) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return repository.save(student);
	}

}
