package com.example.demo.entity;

import java.io.Serializable;

public class SelectCourseId implements Serializable{

	private static final long serialVersionUID = 1L;
	private String studentId;
	private String courseNumber;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
