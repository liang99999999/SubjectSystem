package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;
import com.example.demo.vo.StudentRequest;
import com.example.demo.vo.StudentResponse;

public interface StudentService {
	
	// 学生データを追加する
	public StudentResponse addStudent(StudentRequest studentRequest);

	// 学生データを削除する
	public StudentResponse deleteStudent(StudentRequest studentRequest);

	// 学生の履修情報を取得する
	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest);
}
