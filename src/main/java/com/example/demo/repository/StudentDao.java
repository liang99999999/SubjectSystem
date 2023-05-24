package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, String> {

	boolean existsByStudentIdAndName(String studentId, String name);

	boolean existsByStudentId(String studentId);

}