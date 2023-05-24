package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Course;
import com.example.demo.entity.SelectCourse;
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

		// 從selectCourseRequest得到要選課的資料
		List<SelectCourse> selectCourseList = selectCourseRequest.getSelectCourseList();

		// 建立空的 List 放最終要選課的資料
		List<SelectCourse> addSelectCourseList = new ArrayList<>();

		// 遍歷選課資料
		for (SelectCourse item : selectCourseList) {
			// 確認學號和姓名跟課程代碼都不得為空白
			if (!StringUtils.hasText(item.getStudentId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCourseNumber())) {
				return new SelectCourseResponse("學號和姓名跟課程代碼不得為空白");
			}

			// 確認學號是否不存在或是學生跟姓名輸入錯誤
			if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
				return new SelectCourseResponse("學號不存在或是學生跟姓名輸入錯誤");
			}

			// 確認學生是否已經選過該課程代碼
			if (selectCourseDao.existsByStudentIdAndNameAndCourseNumber(item.getStudentId(), item.getName(),
					item.getCourseNumber())) {
				return new SelectCourseResponse("學生已選過此課程代碼");
			}

			Optional<Course> CourseOp = courseDao.findById(item.getCourseNumber());

			if (!courseDao.existsById(item.getCourseNumber())) {
				return new SelectCourseResponse("課程代碼不存在");
			}

			Course courseItem = CourseOp.get();

			SelectCourse courseSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
					item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
					courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());

			// 確認學生是否選過相同名稱課程
			if (selectCourseDao.existsByStudentIdAndNameAndCourseName(item.getStudentId(), item.getName(),
					courseItem.getCourseName())) {
				return new SelectCourseResponse("學生已選過相同名稱課程");
			}

			List<SelectCourse> CreditThanTen = selectCourseDao.findByStudentIdAndName(item.getStudentId(),
					item.getName());

			int totalCredit = courseItem.getCredit();
			// 遍歷學生選過課程的學分
			for (SelectCourse courseCredit : CreditThanTen) {
				totalCredit += courseCredit.getCredit();
			}
			// 確認選課學分數是否已超過10學分
			if (totalCredit > 10) {
				return new SelectCourseResponse("學生選課學分數已超過10學分");
			}

			// 遍歷學生選過課程的時間和日期資訊
			for (SelectCourse courseTime : CreditThanTen) {
				// 現在選的課程開始時間早於已選的課程結束時間就會衝堂,
				// 和現在選的課程結束時間早於已選的課程開始時間也會衝堂,
				if (courseItem.getStartTime().isBefore(courseTime.getEndTime())
						&& courseItem.getEndTime().isAfter(courseTime.getStartTime())
						&& courseItem.getSchoolDay().equals(courseTime.getSchoolDay())) {
					return new SelectCourseResponse("跟原有的選課衝堂");
				}
			}
			// 用課程代碼查找選課的資訊
			List<SelectCourse> classThanThree = selectCourseDao.findByCourseNumber(item.getCourseNumber());

			int count = 1;
			// 遍歷選課裡面的課程代碼資料
			for (SelectCourse courseNumber : classThanThree) {
				if (item.getCourseNumber().equals(courseNumber.getCourseNumber())) {
					count++;
				}

				// 確認選這堂課的人是否超過3個
				if (count > 3) {
					return new SelectCourseResponse("這堂課已經個3人選課,無法在選這堂課");
				}
			}
			// 將選課資料加入到空的List
			addSelectCourseList.add(courseSelectCourse);
		}

		// 儲存進去資料庫已經選課的資料
		selectCourseDao.saveAll(addSelectCourseList);

		// 回傳選課資料及選課成功訊息
		return new SelectCourseResponse(addSelectCourseList, "選課成功");
	}

	@Override
	public SelectCourseResponse deleteSelectCourse(SelectCourseRequest selectCourseRequest) {
		
		// 從請求中取得待新增的課程清單
		List<SelectCourse> selectCourseList = selectCourseRequest.getSelectCourseList();
<<<<<<< HEAD

		// 建立空的 List 放沒問題的資料
		List<SelectCourse> deleteSelectList = new ArrayList<>();

=======
		
		// 建立空的 List 放沒問題的資料
		List<SelectCourse> deleteSelectList = new ArrayList<>();
		
>>>>>>> 7a859556eeceafca50f802357e2a6c68c1c93bfd
		// 遍歷課程列表
		for (SelectCourse item : selectCourseList) {
			// 確認學號和姓名跟課程代碼不得為空白
			if (!StringUtils.hasText(item.getStudentId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCourseNumber())) {
				return new SelectCourseResponse("學號和姓名跟課程代碼不得為空白");
			}
<<<<<<< HEAD

			// 確認學號是否不存在或是學生跟姓名錯誤
			if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
				return new SelectCourseResponse("學號不存在或是學生跟姓名錯誤");
			}

			List<SelectCourse> selectCourseAgain = selectCourseDao
					.findByStudentIdAndNameAndCourseNumber(item.getStudentId(), item.getName(), item.getCourseNumber());

			if (selectCourseAgain.isEmpty()) {
				return new SelectCourseResponse("學生未選過該課程代碼");
			}

			// 遍歷學生選過的課程代碼
			for (SelectCourse courseItem : selectCourseAgain) {
				// 將選課資料加入要刪除的清單中
				SelectCourse deleteSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
						item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
						courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());

=======
			
			//確認學號是否不存在或是學生跟姓名錯誤
			if (!studentDao.existsByStudentIdAndName(item.getStudentId(), item.getName())) {
				return new SelectCourseResponse("學號不存在或是學生跟姓名錯誤");
			}
			
			List<SelectCourse> selectCourseAgain = selectCourseDao
					.findByStudentIdAndNameAndCourseNumber(item.getStudentId(), item.getName(),
							item.getCourseNumber());
			
			if (selectCourseAgain.isEmpty()) {
				return new SelectCourseResponse("學生未選過該課程代碼");
			}
			
			//遍歷學生選過的課程代碼
			for (SelectCourse courseItem : selectCourseAgain) {
				//將選課資料加入要刪除的清單中
				SelectCourse deleteSelectCourse = new SelectCourse(item.getStudentId(), item.getName(),
						item.getCourseNumber(), courseItem.getCourseName(), courseItem.getSchoolDay(),
						courseItem.getStartTime(), courseItem.getEndTime(), courseItem.getCredit());
				
>>>>>>> 7a859556eeceafca50f802357e2a6c68c1c93bfd
				// 新增課程資料到空的 List 裡面
				deleteSelectList.add(deleteSelectCourse);
			}
		}
<<<<<<< HEAD

		// 刪除選課資料
		selectCourseDao.deleteAll(deleteSelectList);

		// 回傳刪除的選課資料及退選成功訊息
		return new SelectCourseResponse(deleteSelectList, "退選成功");
=======
		
		//刪除選課資料
		selectCourseDao.deleteAll(deleteSelectList);
		
		// 回傳刪除的選課資料及刪除成功訊息
		return new SelectCourseResponse(deleteSelectList, "刪掉學生選課");
>>>>>>> 7a859556eeceafca50f802357e2a6c68c1c93bfd
	}
}