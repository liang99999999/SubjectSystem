package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ifs.CourseService;
import com.example.demo.service.ifs.SelectCourseService;
import com.example.demo.service.ifs.StudentService;
import com.example.demo.vo.CourseRequest;
import com.example.demo.vo.CourseResponse;

@CrossOrigin
@RestController
public class CourseController {

	@Autowired
	public SelectCourseService selectCourseService;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public StudentService studentService;


	@PostMapping("/add_course")
	public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
		return courseService.addCourse(courseRequest);
	}

	@PostMapping("/delete_course")
	public CourseResponse deleteCourse(@RequestBody CourseRequest courseRequest) {
		return courseService.deleteCourse(courseRequest);
	}

	@PostMapping("/get_cource_by_course_number")
	public CourseResponse getCourseByCourseNumber(@RequestBody CourseRequest courseRequest) {
		return courseService.getCourseByCourseNumber(courseRequest);
	}

	@PostMapping("/get_cource_by_course_name")
	public CourseResponse getCourseByCourseName(@RequestBody CourseRequest courseRequest) {
		return courseService.getCourseByCourseName(courseRequest);
	}

	@PostMapping("/update_course")
	public CourseResponse updateCourse(@RequestBody CourseRequest courseRequest) {
		return courseService.updateCourse(courseRequest);
	}
}