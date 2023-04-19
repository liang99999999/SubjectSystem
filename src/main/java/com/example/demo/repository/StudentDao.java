package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, String> {

	List<Student> findAllByStudentId(String selectCourseItem);

	List<Student> findByStudentIdAndName(String studentId, String name);

	List<Student> findByStudentId(String studentId);

	boolean existsByStudentIdAndName(String studentId, String name);



}


//@Repository
//public interface StudentDao extends JpaRepository<Student, String> {
//
//	List<Student> findAllByStudentId(String selectCourseItem);
//
//	List<Student> findByStudentIdAndName(String studentId, String name);
//
//	List<Student> findByStudentId(String studentId);
//
//}
