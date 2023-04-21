package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SelectCourse;

@Repository
public interface SelectCourseDao extends JpaRepository<SelectCourse, String> {

	boolean existsByStudentId(String studentId);

	boolean existsByCourseNumber(String courseNumber);

	boolean existsByStudentIdAndName(String studentId, String name);

	boolean existsByStudentIdAndNameAndCourseNumber(String studentId, String name, String courseNumber);

	boolean existsByStudentIdAndNameAndCourseName(String studentId, String name, String courseName);

	List<SelectCourse> findByStudentIdAndNameAndCourseNumber(String studentId, String name, String courseNumber);

	List<SelectCourse> findByStudentId(String studentId);

	List<SelectCourse> findByStudentIdAndName(String studentId, String name);

	List<SelectCourse> findByCourseNumber(String courseNumber);


}