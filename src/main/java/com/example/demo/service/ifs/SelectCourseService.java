package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;

public interface SelectCourseService {
	
	// 選課
	public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest);

	// 退選
	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest);
}