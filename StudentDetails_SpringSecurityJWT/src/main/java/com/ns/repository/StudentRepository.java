package com.ns.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ns.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	

	Student insert(Student student);

	Optional<Student> findById(Long id);
	@Query("select s from Student s where s.id = :id")
    Stream<Student> findByIdReturnStream(@Param("id") Long id);

}
