package com.example.demo.service.ifs;

import com.example.demo.vo.CourseRequest;
import com.example.demo.vo.CourseResponse;

public interface CourseService {

	// 新增課程
	public CourseResponse addCourse(CourseRequest courseRequest);

	// 刪除課程
	public CourseResponse deleteCourse(CourseRequest courseRequest);

	// 用課程代碼找課程資料
	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest);

	// 用課程名稱找課程資料
	public CourseResponse getCourseByCourseName(CourseRequest courseRequest);

	// 修改課程資料
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
