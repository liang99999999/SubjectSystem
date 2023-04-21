package com.example.demo.service.ifs;

import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;
import com.example.demo.vo.StudentRequest;
import com.example.demo.vo.StudentResponse;

public interface StudentService {
	// 新增學生
	public StudentResponse addStudent(StudentRequest studentRequest);

	// 刪除學生
	public StudentResponse deleteStudent(StudentRequest studentRequest);

	// 尋找學生選課資料
	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest);
}


//public interface StudentService {
//	
//	public StudentResponse addStudent(StudentRequest studentRequest);
//	
//	public StudentResponse deleteStudent(StudentRequest studentRequest);
//	
//	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest);
//}