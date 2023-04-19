package com.example.demo.vo;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

	@JsonProperty("course")
	private Course course;

	private List<Course> courseList;
	
	private Set<Course> courseSet;

	private String message;

	public CourseResponse() {

	}

	public CourseResponse(Course course, String message) {
		super();
		this.course = course;
		this.message = message;
	}

	public CourseResponse(List<Course> courseList, String message) {
		super();
		this.courseList = courseList;
		this.message = message;
	}

	public CourseResponse(String message) {
		super();
		this.message = message;
	}



	public CourseResponse(Set<Course> courseSet, String message) {
		super();
		this.courseSet = courseSet;
		this.message = message;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setSubjectSystemList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}



//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class CourseResponse {
//
//	@JsonProperty("course")
//	private Course course;
//
//	private List<Course> courseList;
//
//	private String message;
//
//	public CourseResponse() {
//
//	}
//
//	public CourseResponse(Course course, String message) {
//		super();
//		this.course = course;
//		this.message = message;
//	}
//
//	public CourseResponse(List<Course> courseList, String message) {
//		super();
//		this.courseList = courseList;
//		this.message = message;
//	}
//
//	public CourseResponse(String message) {
//		super();
//		this.message = message;
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
//	public List<Course> getCourseList() {
//		return courseList;
//	}
//
//	public void setSubjectSystemList(List<Course> courseList) {
//		this.courseList = courseList;
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
//}
