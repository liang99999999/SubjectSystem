package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.SelectCourse;
import com.example.demo.entity.Student;
import com.example.demo.repository.SelectCourseDao;
import com.example.demo.repository.StudentDao;
import com.example.demo.service.ifs.StudentService;
import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;
import com.example.demo.vo.StudentRequest;
import com.example.demo.vo.StudentResponse;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SelectCourseDao selectCourseDao;

	@Override
	public StudentResponse addStudent(StudentRequest studentRequest) {
		
		// 從 studentRequest 得到要新增的課程資料
		List<Student> studentList = StudentRequest.getStudentList();
		
		// 遍歷學生資料
		for (Student item : studentList) {
			// 確認學號及學生姓名不得為空白
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
				return new StudentResponse("學號和學生姓名不得為空白");
			}
			// 確認學號是否存在
			if (studentDao.existsById(item.getStudentId())) {
				return new StudentResponse("學號已存在");
			}
		}
		// 新增學生資料
		studentDao.saveAll(studentList);	
		// 回傳新增的學生資料和新增成功訊息
		return new StudentResponse(studentList, "新增學生成功");
	}

	@Override
	public StudentResponse deleteStudent(StudentRequest studentRequest) {
		// 從 studentRequest 得到要新增的課程資料
	    List<Student> deleteStudentList = StudentRequest.getStudentList();
	    // 遍歷學生資料
	    for (Student item : deleteStudentList) {
	    	// 確認學號及學生姓名不得為空白
	        if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
	            return new StudentResponse("學號和學生姓名不得為空白");
	        }
	        // 確認學號是否不存在或是學生跟姓名錯誤
	        if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
	        	return new StudentResponse("學號不存在或是學生跟姓名錯誤");
	        }
	        // 確認學號學生是否還有選課
	        if(selectCourseDao.existsByStudentIdAndName(item.getStudentId(), item.getName())){
	        	return new StudentResponse("學號學生還有選課無法刪除");
	        }
	    }	    
	    //刪除學生資料
	    studentDao.deleteAll(deleteStudentList);
	    //回傳刪除的學生清單及刪除成功訊息
	    return new StudentResponse(deleteStudentList, "刪除學號成功");
	}

	@Override
	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest) {
		
		// 從 selectCourseRequest 取得要查詢的課程名稱列表
		List<SelectCourse> studentDataList = selectCourseRequest.getSelectCourseList();
		
		// 建立空的 List 放沒問題的資料
		List<SelectCourse> SelectCourseList = new ArrayList<>();
		
		// 遍歷選課資料
		for (SelectCourse item : studentDataList) {
			// 確認學號及學生姓名都不得為空白
			if (!StringUtils.hasText(item.getStudentId())) {
				return new SelectCourseResponse("學號不得為空白");
			}
			// 確認學號是否不存在
			if(!studentDao.existsByStudentId(item.getStudentId())) {
				return new SelectCourseResponse("學號不存在");
			}
			//用學號查詢有沒有選課資料
			List<SelectCourse> selectCourse = selectCourseDao.findByStudentId(item.getStudentId());
			//確認學生是否沒有選課
			if (selectCourse.isEmpty()) {
				return new SelectCourseResponse("學號學生沒有選課");
			}
			//新增查詢的學生資料
			SelectCourseList.addAll(selectCourse);
		}
		// 回傳查詢的學生資料及查詢成功訊息
		return new SelectCourseResponse(SelectCourseList, "學生資料取得成功");
	}
}