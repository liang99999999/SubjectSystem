package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;
import com.example.demo.vo.StudentRequest;
import com.example.demo.vo.StudentResponse;

public interface StudentService {
	
	public StudentResponse addStudent(StudentRequest studentRequest);
	
	public StudentResponse deleteStudent(StudentRequest studentRequest);
	
	public SelectCourseResponse getStudentData(SelectCourseRequest studentRequest);
}


//public interface StudentService {
//	
//	public StudentResponse addStudent(StudentRequest studentRequest);
//	
//	public StudentResponse deleteStudent(StudentRequest studentRequest);
//	
//	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest);
//}