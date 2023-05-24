package com.example.demo.vo;

import java.util.List;

import com.example.demo.entity.Course;

public class CourseRequest {

	private Course course;

	private List<Course> courseList;

	public CourseRequest() {
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

	public void setCoursetemList(List<Course>courseList) {
		this.courseList = courseList;
	}

}
