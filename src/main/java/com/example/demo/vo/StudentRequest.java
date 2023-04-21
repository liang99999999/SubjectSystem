package com.example.demo.vo;

import java.util.List;

import com.example.demo.entity.Student;


public class StudentRequest {

	private Student student;
	
	private static List<Student> studentList;

	public StudentRequest() {
		
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public static List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		StudentRequest.studentList = studentList;
	}

}

//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class StudentRequest {
//
//	private Student student;
//	
//	private static List<Student> studentList;
//
//	public StudentRequest() {
//		
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
//	public static List<Student> getStudentList() {
//		return studentList;
//	}
//
//	public void setStudentList(List<Student> studentList) {
//		StudentRequest.studentList = studentList;
//	}
//
//}
