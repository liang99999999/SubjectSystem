package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Course;
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

//	@Autowired
//	private CourseDao courseDao;

	@Autowired
	private SelectCourseDao selectCourseDao;

	@Override
	public StudentResponse addStudent(StudentRequest StudentRequest) {
		// 從 StudentRequest 對象中獲取學生列表
		List<Student> studentList = StudentRequest.getStudentList();
		// 建立一個空的錯誤學生列表
		List<Student> errorStudentList = new ArrayList<>();
		// 遍歷學生列表，檢查學生數據是否有效
		for (Student item : studentList) {
			// 檢查學生姓名和學生ID是否都不為空
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
				// 如果其中有任何一項為空，返回一個包含錯誤信息的 StudentResponse 對象
				return new StudentResponse("學號和學生姓名不得為空白");
			}
			// 檢查學生ID是否已經存在於數據庫中
			if (studentDao.existsById(item.getStudentId())) {
				// 如果學生ID已經存在，則將該學生添加到錯誤學生列表中
				errorStudentList.add(item);
			}
		}
		// 如果存在任何錯誤學生，返回一個包含錯誤信息的 StudentResponse 對象
		if (!errorStudentList.isEmpty()) {
			return new StudentResponse("學號已存在");
		}
		// 如果所有學生數據都有效，將學生數據保存到數據庫中
		studentDao.saveAll(studentList);
		
		// 返回一個包含新增學生列表和成功信息的 StudentResponse 對象
		return new StudentResponse(studentList, "新增學生成功");
	}

	@Override
	public StudentResponse deleteStudent(StudentRequest studentRequest) {
	    List<Student> deleteStudentList = StudentRequest.getStudentList();

	    List<Student> errorStudentList = new ArrayList<>();

	    for (Student item : deleteStudentList) {
	        if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
	            return new StudentResponse("學號和學生姓名不得為空白");
	        }
	        if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
	            errorStudentList.add(item);
	        }
	    }
	    if (!errorStudentList.isEmpty()) {
	        return new StudentResponse("學生不存在");
	    }

	    for (Student item : deleteStudentList) {
	        List<SelectCourse> studentCourseList = selectCourseDao.findByStudentIdAndName(item.getStudentId(), item.getName());
	        if (!studentCourseList.isEmpty()) {
	            return new StudentResponse("學號學生還有選課無法刪除");
	        }
	    }
	    studentDao.deleteAll(deleteStudentList);

	    if (deleteStudentList.isEmpty()) {
	        return new StudentResponse("課程資料取得失敗");
	    }
	    return new StudentResponse(deleteStudentList, "刪除學號成功");
	}

	@Override
	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest) {
		// 從 SubjectSystemRequest 取得要查詢的課程名稱列表
		List<SelectCourse> studentDataList = selectCourseRequest.getSelectCourseList();
		// 建立一個結果列表
		List<SelectCourse> resultList = new ArrayList<>();

		// 依序處理每個課程名稱
		for (SelectCourse item : studentDataList) {
			String studentDataItem = item.getStudentId();
			// 檢查課程名稱是否為空
			if (!StringUtils.hasText(item.getStudentId())) {
				return new SelectCourseResponse("學號不得為空");
			}
			List<Student> Student = studentDao.findByStudentId(item.getStudentId());
			// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
			if (Student.isEmpty()) {
				return new SelectCourseResponse("無此學號學生");
			}
			// 以課程名稱查詢所有符合條件的課程
			List<SelectCourse> selectCourse = selectCourseDao.findAllByStudentId(studentDataItem);
			// 如果查詢結果為空，代表該學號不存在，回傳錯誤訊息
			if (selectCourse.isEmpty()) {
				return new SelectCourseResponse("學號學生沒有選課");
			} else {
				// 如果查詢結果不為空，將所有查詢結果加入結果列表
				resultList.addAll(selectCourse);
			}
		}
		// 如果結果列表為空，代表所有課程名稱都不存在，回傳錯誤訊息
		if (resultList.isEmpty()) {
			return new SelectCourseResponse("學生資料取得失敗");
		}
		// 如果結果列表不為空，代表有查詢到資料，回傳成功訊息及查詢結果
		return new SelectCourseResponse(resultList, "學生資料取得成功");
	}

}




//@Service
//public class StudentServiceImpl implements StudentService {
//
//	@Autowired
//	private StudentDao studentDao;
//
//	@Autowired
//	private CourseDao courseDao;
//
//	@Autowired
//	private SelectCourseDao selectCourseDao;
//
//	@Override
//	public StudentResponse addStudent(StudentRequest StudentRequest) {
//		// 建立一個空的錯誤學生列表
//		List<Student> errorStudentList = new ArrayList<>();
//		// 從 StudentRequest 對象中獲取學生列表
//		List<Student> studentList = StudentRequest.getStudentList();
//		// 遍歷學生列表，檢查學生數據是否有效
//		for (Student item : studentList) {
//			// 檢查學生姓名和學生ID是否都不為空
//			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
//				// 如果其中有任何一項為空，返回一個包含錯誤信息的 StudentResponse 對象
//				return new StudentResponse("學號和學生姓名不得為空白");
//			}
//			// 檢查學生ID是否已經存在於數據庫中
//			if (studentDao.existsById(item.getStudentId())) {
//				// 如果學生ID已經存在，則將該學生添加到錯誤學生列表中
//				errorStudentList.add(item);
//			}
//		}
//		// 如果存在任何錯誤學生，返回一個包含錯誤信息的 StudentResponse 對象
//		if (!errorStudentList.isEmpty()) {
//			return new StudentResponse("學號已存在");
//		}
//		// 如果所有學生數據都有效，將學生數據保存到數據庫中
//		studentDao.saveAll(studentList);
//		// 返回一個包含新增學生列表和成功信息的 StudentResponse 對象
//		return new StudentResponse(studentList, "新增學生成功");
//	}
//
//	@Override
//	public StudentResponse deleteStudent(StudentRequest studentRequest) {
//		List<Student> errorStudentList = new ArrayList<>();
//
//		List<Student> deleteStudentList = StudentRequest.getStudentList();
//
//		for (Student item : deleteStudentList) {
//			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
//				return new StudentResponse("學號和學生姓名不得為空白");
//			}
//			if (studentDao.existsById(item.getStudentId())) {
//				errorStudentList.add(item);
//			}
//			if (errorStudentList.isEmpty()) {
//				return new StudentResponse("學號不存在");
//			}
//			List<SelectCourse> studentOpt = selectCourseDao.findByStudentIdAndName(item.getStudentId(), item.getName());
//			// 如果查詢的 Optional 為空，代表該課程代碼不存在
//			if (!studentOpt.isEmpty()) {
//				return new StudentResponse("學號學生還有選課無法刪除");
//			}
//		}
//		studentDao.deleteAll(deleteStudentList);
//		return new StudentResponse(deleteStudentList, "刪除學號成功");
//	}
//
//	@Override
//	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest) {
//		// 從 SubjectSystemRequest 取得要查詢的課程名稱列表
//		List<SelectCourse> studentDataList = selectCourseRequest.getSelectCourseList();
//		// 建立一個結果列表
//		List<SelectCourse> resultList = new ArrayList<>();
//
//		// 依序處理每個課程名稱
//		for (SelectCourse item : studentDataList) {
//			String studentDataItem = item.getStudentId();
//			// 檢查課程名稱是否為空
//			if (!StringUtils.hasText(item.getStudentId())) {
//				return new SelectCourseResponse("學號不得為空");
//			}
//			List<Student> Student = studentDao.findByStudentId(item.getStudentId());
//			// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
//			if (Student.isEmpty()) {
//				return new SelectCourseResponse("無此學號學生");
//			}
//			// 以課程名稱查詢所有符合條件的課程
//			List<SelectCourse> selectCourse = selectCourseDao.findAllByStudentId(studentDataItem);
//			// 如果查詢結果為空，代表該學號不存在，回傳錯誤訊息
//			if (selectCourse.isEmpty()) {
//				return new SelectCourseResponse("學號學生沒有選課");
//			} else {
//				// 如果查詢結果不為空，將所有查詢結果加入結果列表
//				resultList.addAll(selectCourse);
//			}
//		}
//		// 如果結果列表為空，代表所有課程名稱都不存在，回傳錯誤訊息
//		if (resultList.isEmpty()) {
//			return new SelectCourseResponse("學生資料取得失敗");
//		}
//		// 如果結果列表不為空，代表有查詢到資料，回傳成功訊息及查詢結果
//		return new SelectCourseResponse(resultList, "學生資料取得成功");
//	}
//
//}