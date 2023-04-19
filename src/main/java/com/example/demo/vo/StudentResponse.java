package com.example.demo.vo;

import java.util.List;

import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
	
	@JsonProperty("student")
	private Student student;
	
	private List<Student> studentList;
	
	private String message;
	
	public StudentResponse() {
		
	}

	public StudentResponse(Student student, String message) {
		super();
		this.student = student;
		this.message = message;
	}

	public StudentResponse(List<Student> studentList, String message) {
		super();
		this.studentList = studentList;
		this.message = message;
	}

	public StudentResponse(String message) {
		super();
		this.message = message;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}



//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class StudentResponse {
//	
//	@JsonProperty("student")
//	private Student student;
//	
//	private List<Student> studentList;
//	
//	private String message;
//	
//	public StudentResponse() {
//		
//	}
//
//	public StudentResponse(Student student, String message) {
//		super();
//		this.student = student;
//		this.message = message;
//	}
//
//	public StudentResponse(List<Student> studentList, String message) {
//		super();
//		this.studentList = studentList;
//		this.message = message;
//	}
//
//	public StudentResponse(String message) {
//		super();
//		this.message = message;
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
//		return studentList;
//	}
//
//	public void setStudentList(List<Student> studentList) {
//		this.studentList = studentList;
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