package com.example.demo.vo;

import java.util.List;

import com.example.demo.entity.Course;
import com.example.demo.entity.SelectCourse;
import com.example.demo.entity.Student;


public class SelectCourseRequest {

	private SelectCourse selectCourse;

	private List<SelectCourse> selectCourseList;

	private Course course;

	private List<Course> courseList;

	private Student student;

	private List<Student> StudentList;

	public SelectCourseRequest() {

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
