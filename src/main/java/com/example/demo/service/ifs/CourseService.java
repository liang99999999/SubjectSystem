package com.example.demo.service.ifs;

import com.example.demo.vo.CourseRequest;
import com.example.demo.vo.CourseResponse;

public interface CourseService {

	// 新しいコースを追加する
	public CourseResponse addCourse(CourseRequest courseRequest);

	// コースを削除する
	public CourseResponse deleteCourse(CourseRequest courseRequest);

	// コースコードでコース情報を取得する
	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest);

	// コース名でコース情報を取得する
	public CourseResponse getCourseByCourseName(CourseRequest courseRequest);

	// コース情報を更新する
	public CourseResponse updateCourse(CourseRequest courseRequest);
}

