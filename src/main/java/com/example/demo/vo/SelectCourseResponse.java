package com.example.demo.vo;

import java.util.List;

import com.example.demo.entity.Course;
import com.example.demo.entity.SelectCourse;
import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectCourseResponse {

	private SelectCourse selectCourse;

	private List<SelectCourse> selectCourseList;

	private Course course;

	private List<Course> courseList;
	
	private Student student;
	
	private List<Student> StudentList;

	private String message;

	public SelectCourseResponse() {

	}

	public SelectCourseResponse(SelectCourse selectCourse, String message) {
		super();
		this.selectCourse = selectCourse;
		this.message = message;
	}

	public SelectCourseResponse(List<SelectCourse> selectCourseList, String message) {
		super();
		this.selectCourseList = selectCourseList;
		this.message = message;
	}

	public SelectCourseResponse(List<SelectCourse> selectCourseList, List<Course> courseList, String message) {
		super();
		this.selectCourseList = selectCourseList;
		this.courseList = courseList;
		this.message = message;
	}

	public SelectCourseResponse(List<SelectCourse> selectCourseList, Course course, String message) {
		super();
		this.selectCourseList = selectCourseList;
		this.course = course;
		this.message = message;
	}

	public SelectCourseResponse(String message) {
		super();
		this.message = message;
	}

	public SelectCourse getSelectCourse() {
		return selectCourse;
	}

	public void setSelectCourse(SelectCourse selectCourse) {
		this.selectCourse = selectCourse;
	}

	public List<SelectCourse> getSelectCourseList() {
		return selectCourseList;
	}

	public void setSelectCourseList(List<SelectCourse> selectCourseList) {
		this.selectCourseList = selectCourseList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return StudentList;
	}

	public void setStudentList(List<Student> studentList) {
		StudentList = studentList;
	}

}



//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class SelectCourseResponse {
//
//	private SelectCourse selectCourse;
//
//	private List<SelectCourse> selectCourseList;
//
//	private Course course;
//
//	private List<Course> courseList;
//	
//	private Student student;
//	
//	private List<Student> StudentList;
//
//	private String message;
//
//	public SelectCourseResponse() {
//
//	}
//
//	public SelectCourseResponse(SelectCourse selectCourse, String message) {
//		super();
//		this.selectCourse = selectCourse;
//		this.message = message;
//	}
//
//	public SelectCourseResponse(List<SelectCourse> selectCourseList, String message) {
//		super();
//		this.selectCourseList = selectCourseList;
//		this.message = message;
//	}
//
//	public SelectCourseResponse(List<SelectCourse> selectCourseList, List<Course> courseList, String message) {
//		super();
//		this.selectCourseList = selectCourseList;
//		this.courseList = courseList;
//		this.message = message;
//	}
//
//	public SelectCourseResponse(List<SelectCourse> selectCourseList, Course course, String message) {
//		super();
//		this.selectCourseList = selectCourseList;
//		this.course = course;
//		this.message = message;
//	}
//
//	public SelectCourseResponse(String message) {
//		super();
//		this.message = message;
//	}
//
//	public SelectCourse getSelectCourse() {
//		return selectCourse;
//	}
//
//	public void setSelectCourse(SelectCourse selectCourse) {
//		this.selectCourse = selectCourse;
//	}
//
//	public List<SelectCourse> getSelectCourseList() {
//		return selectCourseList;
//	}
//
//	public void setSelectCourseList(List<SelectCourse> selectCourseList) {
//		this.selectCourseList = selectCourseList;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public List<Course> getCourseList() {
//		return courseList;
//	}
//
//	public void setCourseList(List<Course> courseList) {
//		this.courseList = courseList;
//	}
//
//	public Course getCourse() {
//		return course;
//	}
//
//	public void setCourse(Course course) {
//		this.course = course;
//	}
//
//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//
//	public List<Student> getStudentList() {
//		return StudentList;
//	}
//
//	public void setStudentList(List<Student> studentList) {
//		StudentList = studentList;
//	}
//
//}
