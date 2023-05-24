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

		// 反復学生データ
		for (Student item : studentList) {
			// 学生番号と名前が空白でないことを確認して
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
				return new StudentResponse("學號和姓名不得為空白");
			}
			// 学生番号が既に存在するかどうかを確認する
			if (studentDao.existsById(item.getStudentId())) {
				return new StudentResponse("學號已存在");
			}
		}
		// 新たに追加された学生データを保存
		studentDao.saveAll(studentList);
		// 追加された学生データと成功メッセージを返します
		return new StudentResponse(studentList, "新增學生成功");
	}

	@Override
	public StudentResponse deleteStudent(StudentRequest studentRequest) {
		// 從 studentRequest 得到要新增的課程資料
		List<Student> deleteStudentList = StudentRequest.getStudentList();
		// 反復学生データ
		for (Student item : deleteStudentList) {
			// 学生番号と名前が空白でないことを確認して
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getStudentId())) {
				return new StudentResponse("學號和姓名不得為空白");
			}
			// 学生番号が存在しないかどうかを確認する
			if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
				return new StudentResponse("學號不存在");
			}
			// 学生が選択科目をしているかどうかを確認する
			if (selectCourseDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
				return new StudentResponse("學號學生還有選課無法刪除");
			}
		}
		// 学生データを削除する
		studentDao.deleteAll(deleteStudentList);
		//削除された学生データと削除成功メッセージを返します
		return new StudentResponse(deleteStudentList, "刪除學號成功");
	}

	@Override
	public SelectCourseResponse getStudentData(SelectCourseRequest selectCourseRequest) {

		// 從 selectCourseRequest 取得要查詢的課程名稱列表
		List<SelectCourse> studentDataList = selectCourseRequest.getSelectCourseList();

		// 建立空的 List 放沒問題的資料
		List<SelectCourse> SelectCourseList = new ArrayList<>();

		// 反復選択科目データ
		for (SelectCourse item : studentDataList) {
			// 学生番号が空白でないことを確認して
			if (!StringUtils.hasText(item.getStudentId())) {
				return new SelectCourseResponse("學號不得為空白");
			}
			// 学生番号が存在しないかどうかを確認する
			if (!studentDao.existsByStudentId(item.getStudentId())) {
				return new SelectCourseResponse("學號不存在");
			}
			// 使用学籍番号で授業を選択していないか検索する
			List<SelectCourse> selectCourse = selectCourseDao.findByStudentId(item.getStudentId());
			if (selectCourse.isEmpty()) {
				return new SelectCourseResponse("學號學生沒有選課");
			}
			// 添增查詢的學生資料
			SelectCourseList.addAll(selectCourse);
		}
		// 回傳查詢的學生資料及查詢成功訊息
		return new SelectCourseResponse(SelectCourseList, "學生資料取得成功");
	}
}