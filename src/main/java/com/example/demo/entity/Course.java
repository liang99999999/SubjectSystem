package com.example.demo.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@Column(name = "course_number")
	private String courseNumber;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "school_day")
	private String schoolDay;
	
	@Column(name = "start_time")
	private LocalTime startTime;
	
	@Column(name = "end_time")
	private LocalTime endTime;
	
	@Column(name = "credit")
	private Integer credit;

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSchoolDay() {
		return schoolDay;
	}

	public void setSchoolDay(String schoolDay) {
		this.schoolDay = schoolDay;
	}

	

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Course(String courseNumber, String courseName, String schoolDay, LocalTime startTime, LocalTime endTime,
			int credit) {
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.schoolDay = schoolDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.credit = credit;
	}

	public Course() {
		super();
	}
	
}


//@Entity
//@Table(name = "course")
//public class Course {
//	
//	@Id
//	@Column(name = "course_number")
//	private String courseNumber;
//	
//	@Column(name = "course_name")
//	private String courseName;
//	
//	@Column(name = "school_day")
//	private String schoolDay;
//	
//	@Column(name = "start_time")
//	private String startTime;
//	
//	@Column(name = "end_time")
//	private String endTime;
//	
//	@Column(name = "credit")
//	private int credit;
//
//	public String getCourseNumber() {
//		return courseNumber;
//	}
//
//	public void setCourseNumber(String courseNumber) {
//		this.courseNumber = courseNumber;
//	}
//
//	public String getCourseName() {
//		return courseName;
//	}
//
//	public void setCourseName(String courseName) {
//		this.courseName = courseName;
//	}
//
//	public String getSchoolDay() {
//		return schoolDay;
//	}
//
//	public void setSchoolDay(String schoolDay) {
//		this.schoolDay = schoolDay;
//	}
//
//	public String getStartTime() {
//		return startTime;
//	}
//
//	public void setStartTime(String startTime) {
//		this.startTime = startTime;
//	}
//
//	public String getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(String endTime) {
//		this.endTime = endTime;
//	}
//
//	public int getCredit() {
//		return credit;
//	}
//
//	public void setCredit(int credit) {
//		this.credit = credit;
//	}
//	
//}
