package com.example.demo.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SelectCourseId.class)
@Table(name = "selectcourse")
public class SelectCourse{

	@Id
	@Column(name = "studentid")
	private String studentId;  //學號

	@Column(name = "name")
	private String name;  //姓名

	@Id
	@Column(name = "course_number")
	private String courseNumber;  //課程代碼

	@Column(name = "course_name")
	private String courseName;  //課程名稱

	@Column(name = "school_day")
	private String schoolDay;  //上課星期幾

	@Column(name = "start_time")
	private LocalTime startTime;;  //開始時間

	@Column(name = "end_time")
	private LocalTime endTime;; //結束時間

	@Column(name = "credit")
	private Integer credit;  //學分

	public SelectCourse(){
		
	}
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public SelectCourse(String studentId, String name, String courseNumber, String courseName, String schoolDay,
			LocalTime startTime, LocalTime endTime, Integer credit) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.schoolDay = schoolDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.credit = credit;
	}

}
