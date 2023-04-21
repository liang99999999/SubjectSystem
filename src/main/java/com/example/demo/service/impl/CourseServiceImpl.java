package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Course;
import com.example.demo.entity.SelectCourse;
import com.example.demo.repository.CourseDao;
import com.example.demo.repository.SelectCourseDao;
import com.example.demo.service.ifs.CourseService;
import com.example.demo.vo.CourseRequest;
import com.example.demo.vo.CourseResponse;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private SelectCourseDao selectCourseDao;

	@Override
	public CourseResponse addCourse(CourseRequest courseRequest) {

		// 從 CourseRequest 得到要新增的課程資料
		List<Course> addCourseList = courseRequest.getCourseList();

		// 遍歷課程資料
		for (Course item : addCourseList) {
			// 確認輸入的課程資料不得為空白
			if (!StringUtils.hasText(item.getCourseName()) || !StringUtils.hasText(item.getCourseNumber())
					|| !StringUtils.hasText(item.getSchoolDay()) || Objects.isNull(item.getStartTime())
					|| Objects.isNull(item.getEndTime())) {
				return new CourseResponse("輸入的課程資料不得為空白");
			}
			
			// 確認學分數不得大於3或小於1
			if (item.getCredit() < 1 || item.getCredit() > 3) {
				return new CourseResponse("輸入課程學分為1~3");
			}
			
			// 確認課程代碼是否已存在
			if (courseDao.existsById(item.getCourseNumber())) {
				return new CourseResponse("課程代碼已存在");
			}
			
		}
		// 新增課程資料
		courseDao.saveAll(addCourseList);

		// 回傳新增的課程清單和新增成功訊息
		return new CourseResponse(addCourseList, "新增課程成功");
	}

	@Override
	public CourseResponse deleteCourse(CourseRequest courseRequest) {
		// 從 CourseRequest 得到要刪除的課程資料
		List<Course> deletecourseList = courseRequest.getCourseList();

		// 建立空的 List 放沒問題的資料
		List<Course> newCourseList = new ArrayList<>();

		// 遍歷課程資料
		for (Course item : deletecourseList) {
			// 確認課程代碼及課程名稱不得為空白
			if (!StringUtils.hasText(item.getCourseNumber()) || !StringUtils.hasText(item.getCourseName())) {
				return new CourseResponse("課程代碼跟課程名稱不得為空白");
			}
			// 確認課程是否已經在資料庫裡面
			List<Course> errorCourseList = courseDao.findByCourseNumberAndCourseName(item.getCourseNumber(),
					item.getCourseName());
			if (errorCourseList.isEmpty()) {
				return new CourseResponse("課程不存在");
			}
			// 確認該課程是否有學生選修
			List<SelectCourse> courseNum = selectCourseDao.findByCourseNumber(item.getCourseNumber());
			for (SelectCourse course : courseNum) {
				if (selectCourseDao.existsByCourseNumber(course.getCourseNumber())) {
					return new CourseResponse("該課程有學生選修無法刪除");
				}
			}
			// 遍歷要刪除的課程
			for (Course courseItem : errorCourseList) {
				// 將課程資料加入要刪除的課程清單中
				Course deleteSelectCourse = new Course(item.getCourseNumber(), courseItem.getCourseName(),
						courseItem.getSchoolDay(), courseItem.getStartTime(), courseItem.getEndTime(),
						courseItem.getCredit());
				// 新增課程資料到新的 List 裡面
				newCourseList.add(deleteSelectCourse);
			}
		}
		// 刪除課程資料
		courseDao.deleteAll(newCourseList);
		// 回傳刪除的課程清單及刪除成功訊息
		return new CourseResponse(newCourseList, "刪除課程成功");
	}

	@Override
	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest) {
		// 從 courseRequest 得到要查詢的課程代碼
		List<Course> courseList = courseRequest.getCourseList();
		// 建立空的 Set 來放沒問題的資料
		Set<Course> courseSet = new LinkedHashSet<>();
		// 遍歷課程資料
		for (Course item : courseList) {
			// 確認課程代碼不得為空
			if (!StringUtils.hasText(item.getCourseNumber())) {
				return new CourseResponse("課程代碼不得為空");
			}
			// 確認課程代碼是否不存在
			List<Course> course = courseDao.findByCourseNumber(item.getCourseNumber());
			if (course.isEmpty()) {
				return new CourseResponse("課程代碼不存在");
			}
			// 新增要查詢的課程資料
			courseSet.addAll(course);
		}
		// 回傳查詢的課程及查詢成功訊息
		return new CourseResponse(courseSet, "課程資料查詢成功");
	}

	@Override
	public CourseResponse getCourseByCourseName(CourseRequest courseRequest) {
		// 從 CourseRequest 得到要查詢的課程名稱
		List<Course> courseList = courseRequest.getCourseList();
		// 建立空的 Set 來放沒問題的資料
		Set<Course> courseSet = new LinkedHashSet<>();
		// 遍歷課程資料
		for (Course item : courseList) {
			// 確認課程名稱不得為空
			if (!StringUtils.hasText(item.getCourseName())) {
				return new CourseResponse("課程名稱不得為空");
			}
			// 用課程名稱查詢課程資料
			List<Course> course = courseDao.findByCourseName(item.getCourseName());
			// 確認課程名稱是否不存在
			if (course.isEmpty()) {
				return new CourseResponse("課程名稱不存在");
			}
			// 新增要查詢的課程資料
			courseSet.addAll(course);
		}
		// 回傳查詢的課程及查詢成功訊息
		return new CourseResponse(courseSet, "課程資料取得成功");
	}

	@Override
	public CourseResponse updateCourse(CourseRequest courseRequest) {
		// 從 CourseRequest 得到要修改的課程
		List<Course> courseList = courseRequest.getCourseList();
		// 建立空的 List 放沒問題的資料
		List<Course> updatedCourseList = new ArrayList<>();
		// 遍歷課程資料
		for (Course item : courseList) {
			// 確認輸入的課程資料不得為空白
			if (!StringUtils.hasText(item.getCourseName()) || !StringUtils.hasText(item.getCourseNumber())
					|| !StringUtils.hasText(item.getSchoolDay()) || Objects.isNull(item.getStartTime())
					|| Objects.isNull(item.getEndTime())) {
				return new CourseResponse("輸入的課程資料不得為空白");
			}
			// 確認學分數不得大於3或小於1
			if (item.getCredit() < 1 || item.getCredit() > 3) {
				return new CourseResponse("輸入課程學分為1~3");
			}
			// 確認課程代碼是不存在
			if (!courseDao.existsById(item.getCourseNumber())) {
				return new CourseResponse("課程代碼不存在");
			} else {
				// 修改課程資料
				Course courseToUpdate = new Course(item.getCourseNumber(), item.getCourseName(),
						item.getSchoolDay(),item.getStartTime(), item.getEndTime(), item.getCredit());
				// 新增修改的課程資料到新的 List 裡面
				updatedCourseList.add(courseToUpdate);
			}
		}
		// 儲存進去資料庫的已經修改好的資料
		courseDao.saveAll(updatedCourseList);
		// 回傳修改過的課程及修改成功訊息
		return new CourseResponse(updatedCourseList, "修改課程成功");
	}
}