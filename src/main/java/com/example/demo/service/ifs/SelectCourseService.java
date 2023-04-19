package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;

public interface SelectCourseService {

	public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest);

	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest);
}



//public interface SelectCourseService {
//
//	public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest);
//
//	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest);
//}