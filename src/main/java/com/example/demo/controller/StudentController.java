package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ifs.CourseService;
import com.example.demo.service.ifs.SelectCourseService;
import com.example.demo.service.ifs.StudentService;
import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;
import com.example.demo.vo.StudentRequest;
import com.example.demo.vo.StudentResponse;

@RestController
public class StudentController {

	@Autowired
	public SelectCourseService selectCourseService;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public StudentService studentService;

	
	@PostMapping("/add_student")
	public StudentResponse addStudent(@RequestBody StudentRequest studentRequest) {
		return studentService.addStudent(studentRequest);
	}
	
	@PostMapping("/delete_student")
	public StudentResponse deleteStudent(@RequestBody StudentRequest studentRequest) {
		
		return studentService.deleteStudent(studentRequest);
	}
	
	@PostMapping("/get_student_data")
	public SelectCourseResponse getStudentData(@RequestBody SelectCourseRequest selectCourseRequest) {
		
		return studentService.getStudentData(selectCourseRequest);
	}
	
}



//@RestController
//public class StudentController {
//
//	@Autowired
//	public SelectCourseService selectCourseService;
//	
//	@Autowired
//	public CourseService courseService;
//	
//	@Autowired
//	public StudentService studentService;
//
//	
//	@PostMapping("/add_student")
//	public StudentResponse addStudent(@RequestBody StudentRequest studentRequest) {
//		return studentService.addStudent(studentRequest);
//	}
//	
//	@PostMapping("/delete_student")
//	public StudentResponse deleteStudent(@RequestBody StudentRequest studentRequest) {
//		
//		return studentService.deleteStudent(studentRequest);
//	}
//	
//	@PostMapping("/get_student_data")
//	public SelectCourseResponse getStudentData(@RequestBody SelectCourseRequest selectCourseRequest) {
//		
//		return studentService.getStudentData(selectCourseRequest);
//	}
//	
//}
