package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Course;
import com.example.demo.entity.SelectCourse;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseDao;
import com.example.demo.repository.SelectCourseDao;
import com.example.demo.repository.StudentDao;
import com.example.demo.service.ifs.SelectCourseService;
import com.example.demo.vo.SelectCourseRequest;
import com.example.demo.vo.SelectCourseResponse;

@Service
public class SelectCourseServiceImpl implements SelectCourseService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private SelectCourseDao selectCourseDao;

	@Override
	public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest) {
		// 從請求中取得待新增的課程清單
		List<SelectCourse> selectCourseList = selectCourseRequest.getSelectCourseList();
	
		// 遍歷課程列表
		for (SelectCourse item : selectCourseList) {
			// 確認每個學號和姓名跟課程代碼都不為空白
			if (!StringUtils.hasText(item.getStudentId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCourseNumber())) {
				return new SelectCourseResponse("學號和姓名跟課程代碼不得為空白");
			}
			// 以學號跟姓名查詢有無學生
			List<Student> student = studentDao.findByStudentIdAndName(item.getStudentId(), (item.getName()));
			// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
			if (student.isEmpty()) {
				return new SelectCourseResponse("學號跟姓名錯誤或不存在無法選課");
			}
			// 查詢是否存在指定的課程代碼
			List<Course> course = courseDao.findByCourseNumber(item.getCourseNumber());
			if (course.isEmpty()) {
				return new SelectCourseResponse("課程代碼不存在");
			}
			// 檢查學生是否已經選過該課程代碼
			List<SelectCourse> selectCourse = selectCourseDao.findByStudentIdAndNameAndCourseNumber(item.getStudentId(),
					item.getName(), item.getCourseNumber());
			if (!selectCourse.isEmpty()) {
				return new SelectCourseResponse("學生已選過此課程代碼");
			}
			List<SelectCourse> selectCourseList1 = new ArrayList<>();
	
			for (Course courseItem : course) {
				SelectCourse courseSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
						item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
						courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());
				selectCourseList1.add(courseSelectCourse);
				List<SelectCourse> selectCourseAgin = selectCourseDao.findByStudentIdAndNameAndCourseName(
						item.getStudentId(), item.getName(), courseItem.getCourseName());
				if (!selectCourseAgin.isEmpty()) {
					return new SelectCourseResponse("學生已選過相同名稱課程");
				}
	
				List<SelectCourse> CreditThanTen = selectCourseDao.findByStudentIdAndName(item.getStudentId(),
						item.getName());
	
				int totalCredit = courseItem.getCredit();
				for (SelectCourse course1 : CreditThanTen) {
					totalCredit += course1.getCredit();
				}
				if (totalCredit > 10) {
					return new SelectCourseResponse("學生選課學分數已超過10學分");
				}
				for (SelectCourse course3 : CreditThanTen) {
					if (courseItem.getStartTime().isBefore(course3.getEndTime())
							&& courseItem.getEndTime().isAfter(course3.getStartTime())
							&& courseItem.getSchoolDay().equals(course3.getSchoolDay())) {
						return new SelectCourseResponse("跟原有的選課衝堂");
					}
				}
				List<SelectCourse> classThanThree = selectCourseDao.findByCourseNumber(item.getCourseNumber());
				int count = 1;
				for (SelectCourse course2 : classThanThree) {
					if (item.getCourseNumber().equals(course2.getCourseNumber())) {
						count++;
					}
					if (count > 3) {
						return new SelectCourseResponse("這堂課已經個3人選課,無法在選這堂課");
					}
				}
			}
			selectCourseDao.saveAll(selectCourseList1);
			// 返回成功選課的回應
			return new SelectCourseResponse(selectCourseList1, "選課成功");
		}
		// 返回選課失敗的回應
		return new SelectCourseResponse("選課失敗");
	}
	


	@Override
	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest) {

		// 從請求中取得待新增的課程清單
		List<SelectCourse> selectCourseList = selectCourseRequest.getSelectCourseList();

		// 遍歷課程列表
		for (SelectCourse item : selectCourseList) {
			// 確認每個學號和姓名跟課程代碼都不為空白
			if (!StringUtils.hasText(item.getStudentId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCourseNumber())) {
				return new SelectCourseResponse("學號和姓名跟課程代碼不得為空白");
			}
			List<SelectCourse> selectCourseAgin = selectCourseDao
					.findByStudentIdAndNameAndCourseNumber(item.getStudentId(), item.getName(), item.getCourseNumber());
			if (selectCourseAgin.isEmpty()) {
				return new SelectCourseResponse("學生未選過該課程代碼");
			}
			List<SelectCourse> CourseList1 = new ArrayList<>();
			for (SelectCourse courseItem : selectCourseAgin) {
				SelectCourse deleteSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
						item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
						courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());
				CourseList1.add(deleteSelectCourse);
			}
			selectCourseDao.deleteAll(CourseList1);
			return new SelectCourseResponse(CourseList1, "刪掉學生選課");
		}
		return new SelectCourseResponse("刪掉學生選課失敗");
	}
}

//@Override
//public SelectCourseResponse addSelectCourse(SelectCourseRequest selectCourseRequest) {
//	// 從請求中取得待新增的課程清單
//	List<SelectCourse> selectCourseList = selectCourseRequest.getSelectCourseList();
//
//	// 遍歷課程列表
//	for (SelectCourse item : selectCourseList) {
//		// 確認每個學號和姓名跟課程代碼都不為空白
//		if (!StringUtils.hasText(item.getStudentId()) || !StringUtils.hasText(item.getName())
//				|| !StringUtils.hasText(item.getCourseNumber())) {
//			return new SelectCourseResponse("學號和姓名跟課程代碼不得為空白");
//		}
//		// 以學號跟姓名查詢有無學生
//		List<Student> student = studentDao.findByStudentIdAndName(item.getStudentId(), (item.getName()));
//		// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
//		if (student.isEmpty()) {
//			return new SelectCourseResponse("學號跟姓名錯誤無法選課");
//		}
//		// 查詢是否存在指定的課程代碼
//		List<Course> course = courseDao.findByCourseNumber(item.getCourseNumber());
//		if (course.isEmpty()) {
//			return new SelectCourseResponse("課程代碼不存在");
//		}
//		// 檢查學生是否已經選過該課程代碼
//		List<SelectCourse> selectCourse = selectCourseDao.findByStudentIdAndNameAndCourseNumber(item.getStudentId(),
//				item.getName(), item.getCourseNumber());
//		if (!selectCourse.isEmpty()) {
//			return new SelectCourseResponse("學生已選過此課程代碼");
//		}
//		List<SelectCourse> selectCourseList1 = new ArrayList<>();
//
//		for (Course courseItem : course) {
//			SelectCourse courseSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
//					item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
//					courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());
//			selectCourseList1.add(courseSelectCourse);
//			List<SelectCourse> selectCourseAgin = selectCourseDao.findByStudentIdAndNameAndCourseName(
//					item.getStudentId(), item.getName(), courseItem.getCourseName());
//			if (!selectCourseAgin.isEmpty()) {
//				return new SelectCourseResponse("學生已選過相同名稱課程");
//			}
//
//			List<SelectCourse> CreditThanTen = selectCourseDao.findByStudentIdAndName(item.getStudentId(),
//					item.getName());
//
//			int totalCredit = courseItem.getCredit();
//			for (SelectCourse course1 : CreditThanTen) {
//				totalCredit += course1.getCredit();
//			}
//			if (totalCredit > 10) {
//				return new SelectCourseResponse("學生選課學分數已超過10學分");
//			}
//			for (SelectCourse course3 : CreditThanTen) {
//				if (courseItem.getStartTime().isBefore(course3.getEndTime())
//						&& courseItem.getEndTime().isAfter(course3.getStartTime())
//						&& courseItem.getSchoolDay().equals(course3.getSchoolDay())) {
//					return new SelectCourseResponse("跟原有的選課衝堂");
//				}
//			}
//			List<SelectCourse> classThanThree = selectCourseDao.findByCourseNumber(item.getCourseNumber());
//			int count = 1;
//			for (SelectCourse course2 : classThanThree) {
//				if (item.getCourseNumber().equals(course2.getCourseNumber())) {
//					count++;
//				}
//				if (count > 3) {
//					return new SelectCourseResponse("這堂課已經個3人選課,無法在選這堂課");
//				}
//			}
//		}
//		selectCourseDao.saveAll(selectCourseList1);
//		// 返回成功選課的回應
//		return new SelectCourseResponse(selectCourseList1, "選課成功");
//	}
//	// 返回選課失敗的回應
//	return new SelectCourseResponse("選課失敗");
//}
