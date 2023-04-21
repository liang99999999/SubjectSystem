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

@RestController
public class SelectCourseController {

	@Autowired
	public SelectCourseService selectCourseService;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public StudentService studentService;


	@PostMapping("/add_select_course")
	public SelectCourseResponse addSelectCourse(@RequestBody SelectCourseRequest selectCourseRequest) {

		return selectCourseService.addSelectCourse(selectCourseRequest);
	}

	@PostMapping("/delete_select_course")
	public SelectCourseResponse deleteSelectCourse(@RequestBody SelectCourseRequest selectCourseRequest) {

		return selectCourseService.deleteSelectCourse(selectCourseRequest);
	}
}
