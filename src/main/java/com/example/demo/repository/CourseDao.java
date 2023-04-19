package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, String> {

	boolean existsBycourseName(String courseName);


	boolean existsByCourseNumber(String courseNumber);

	List<Course> findByCourseNumber(String courseNumber);

	List<Course> findByCourseNumberAndCourseName(String courseNumber, String courseName);

	List<Course> findByCourseName(String subjectItem);


	List<Course> findAllByCourseName(String subjectItem);


}


//@Repository
//public interface CourseDao extends JpaRepository<Course, String> {
//
//	boolean existsBycourseName(String courseName);
//
//	List<Course> findAllByCourseName(String subjectItem);
//
//	boolean existsByCourseNumber(String courseNumber);
//
//	List<Course> findByCourseNumber(String courseNumber);
//
//
//}
