package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;

public interface SelectCourseService {
	
	// コースを選択する
	public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest);

	// コースを撤回する
	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest);
}