package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SelectCourse;

@Repository
public interface SelectCourseDao extends JpaRepository<SelectCourse, String> {


	void save(List<SelectCourse> selectCourseList);

	boolean existsByStudentId(String studentId);

	List<SelectCourse> findByStudentIdAndNameAndCourseNumber(String studentId, String name, String courseNumber);

	List<SelectCourse> findAllByStudentId(String studentDataItem);

	List<SelectCourse> findByStudentId(String studentId);

	List<SelectCourse> findByStudentIdAndName(String studentId, String name);

	List<SelectCourse> findByCourseNumber(String courseNumber);

	List<SelectCourse> findByCourseNumberAndCourseName(String courseNumber, String courseName);

	boolean existsByCourseNumber(String courseNumber);

	List<SelectCourse> findByStudentIdAndNameAndCourseName(String studentId, String name, String courseName);


	
}


//@Repository
//public interface SelectCourseDao extends JpaRepository<SelectCourse, String> {
//
//
//	void save(List<SelectCourse> selectCourseList);
//
//	boolean existsByStudentId(String studentId);
//
//	List<SelectCourse> findByStudentIdAndNameAndCourseNumber(String studentId, String name, String courseNumber);
//
//	List<SelectCourse> findAllByStudentId(String studentDataItem);
//
//	Optional<SelectCourse> findByStudentId(String studentId);
//
//	List<SelectCourse> findByStudentIdAndName(String studentId, String name);
//
//	List<SelectCourse> findByStudentIdAndNameAndCourseName(String studentId, String name, String courseName);
//
//	List<SelectCourse> findByCourseNumber(String courseNumber);
//	
//
//}
