package com.example.demo.service.ifs;

import com.example.demo.vo.CourseRequest;
import com.example.demo.vo.CourseResponse;

public interface CourseService {
	
	public CourseResponse addCourse(CourseRequest courseRequest);
	
	public CourseResponse deleteCourse(CourseRequest courseRequest);

	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest);

	public CourseResponse getCourseByCourseName(CourseRequest courseRequest);
	
	public CourseResponse updateCourse(CourseRequest courseRequest);
}


//public interface CourseService {
//	
//	public CourseResponse addCourse(CourseRequest subjectSystemRequest);
//	
//	public CourseResponse deleteCourse(CourseRequest subjectSystemRequest);
//
//	public CourseResponse getCourseByCourseNumber(CourseRequest subjectSystemRequest);
//
//	public CourseResponse getCourseByCourseName(CourseRequest subjectSystemRequest);
//}
