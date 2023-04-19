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

//	@Autowired
//	private StudentDao studentDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private SelectCourseDao selectCourseDao;

	@Override
	public CourseResponse addCourse(CourseRequest courseRequest) {

		// 新增一個空的課程清單，用來存放已存在的課程
		List<Course> errorCourseList = new ArrayList<>();

		// 從請求中取得待新增的課程清單
		List<Course> courseList = courseRequest.getCourseList();

		// 遍歷課程列表
		for (Course item : courseList) {
			// 確認每個課程代碼及課程名稱都不為空白
			if (!StringUtils.hasText(item.getCourseName()) || !StringUtils.hasText(item.getCourseNumber())||
					!StringUtils.hasText(item.getSchoolDay())||Objects.isNull(item.getStartTime())||
					Objects.isNull(item.getEndTime())) {
				return new CourseResponse("課程代碼跟課程名稱不得為空白");
			}
			if(item.getCredit()<1||item.getCredit()>3) {
				return new CourseResponse("輸入課程學分為1~3");
			}
				
			// 確認課程代碼是否已存在於資料庫中
			if (courseDao.existsById(item.getCourseNumber())) {
				// 若已存在，則將該課程加入已存在的清單中
				errorCourseList.add(item);
			}
		}

		if (!errorCourseList.isEmpty()) {
			// 若已存在的清單不為空，表示有課程代碼已存在，回傳錯誤訊息
			return new CourseResponse("課程代碼已存在");
		}

		// 若課程代碼都不存在，則一次性新增所有課程
		courseDao.saveAll(courseList);

		// 回傳新增成功訊息及新增的課程清單
		return new CourseResponse(courseList, "新增課程成功");
	}

	@Override
	public CourseResponse deleteCourse(CourseRequest courseRequest) {
		// 從請求中取得待刪除的課程清單
		List<Course> deletecourseList = courseRequest.getCourseList();

		// 遍歷課程列表
		for (Course item : deletecourseList) {
			// 確認每個課程代碼及課程名稱都不為空白
			if (!StringUtils.hasText(item.getCourseNumber()) || !StringUtils.hasText(item.getCourseName())) {
				// 若課程代碼或課程名稱為空白，則回傳錯誤訊息
				return new CourseResponse("課程代碼跟課程名稱不得為空白");
			}
			// 確認課程代碼是否已存在於資料庫中
			List<Course> errorCourseList = courseDao.findByCourseNumberAndCourseName(item.getCourseNumber(),
					item.getCourseName());

			if (errorCourseList.isEmpty()) {
				// 若課程代碼不存在，則回傳錯誤訊息
				return new CourseResponse("課程不存在");
			}
			// 確認該課程是否有學生選修，若有則回傳錯誤訊息
			List<SelectCourse> courseNum = selectCourseDao.findByCourseNumber(item.getCourseNumber());
			for (SelectCourse course : courseNum) {
				if (selectCourseDao.existsByCourseNumber(course.getCourseNumber())) {
					return new CourseResponse("該課程有學生選修無法刪除");
				}
			}
			// 建立要刪除的課程清單
			List<Course> newCourseList = new ArrayList<>();
			for (Course courseItem : errorCourseList) {
				// 將課程清單加入要刪除的課程清單中
				Course deleteSelectCourse = new Course(item.getCourseNumber(), courseItem.getCourseName(),
						courseItem.getSchoolDay(), courseItem.getStartTime(), courseItem.getEndTime(),
						courseItem.getCredit());
				newCourseList.add(deleteSelectCourse);
			}
			// 刪除課程
			courseDao.deleteAll(newCourseList);
			// 回傳刪除成功訊息及刪除的課程清單
			return new CourseResponse(newCourseList, "刪除課程成功");
		}
		// 若無法刪除課程，則回傳錯誤訊息
		return new CourseResponse("刪除課程失敗");
	}

	@Override
	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest) {
		// 從 courseRequest 取得要查詢的課程代碼列表
		List<Course> courseList = courseRequest.getCourseList();

		// 建立一個結果列表
		Set<Course> resultSet = new LinkedHashSet<>();
		// 遍歷每一筆課程資料
		for (Course item : courseList) {
			// 檢查單筆資料(item)，課程代碼不得為空
			if (!StringUtils.hasText(item.getCourseNumber())) {
				return new CourseResponse("課程代碼不得為空");
			}
			// 根據課程代碼查詢資料庫，回傳 Optional 類型的資料
			List<Course> course = courseDao.findByCourseNumber(item.getCourseNumber());
			// 如果查詢的 Optional 為空，代表該課程代碼不存在
			if (course.isEmpty()) {
				return new CourseResponse("課程代碼不存在");
			}
			// 取得查詢到的課程資料
			resultSet.addAll(course);
		}
		// 如果結果列表為空，代表所有課程名稱都不存在，回傳錯誤訊息
		if (resultSet.isEmpty()) {
			return new CourseResponse("課程資料取得失敗");
		}
		// 如果結果列表不為空，代表有查詢到資料，回傳成功訊息及查詢結果
		return new CourseResponse(resultSet, "課程資料取得成功");
	}

	@Override
	public CourseResponse getCourseByCourseName(CourseRequest courseRequest) {
		// 從 SubjectSystemRequest 取得要查詢的課程名稱列表
		List<Course> courseList = courseRequest.getCourseList();
		// 建立一個結果列表
		Set<Course> resultSet = new LinkedHashSet<>();
		// 依序處理每個課程名稱
		for (Course item : courseList) {
			String subjectItem = item.getCourseName();
			// 檢查課程名稱是否為空
			if (!StringUtils.hasText(item.getCourseName())) {
				return new CourseResponse("課程名稱不得為空");
			}
			// 以課程名稱查詢所有符合條件的課程
			List<Course> course = courseDao.findAllByCourseName(subjectItem);
			// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
			if (course.isEmpty()) {        // <== 這裡有錯，第一個如果不存在，之後就都查不到
				return new CourseResponse("課程名稱不存在");
			}
			// 如果查詢結果不為空，將所有查詢結果加入結果列表
			resultSet.addAll(course);
		}
		// 如果結果列表為空，代表所有課程名稱都不存在，回傳錯誤訊息
		if (resultSet.isEmpty()) {
			return new CourseResponse("課程資料取得失敗");
		}
		// 如果結果列表不為空，代表有查詢到資料，回傳成功訊息及查詢結果
		return new CourseResponse(resultSet, "課程資料取得成功");
	}

	@Override
	public CourseResponse updateCourse(CourseRequest courseRequest) {
	    List<Course> updatedCourseList = new ArrayList<>();
	    List<Course> errorCourseList = new ArrayList<>();

	    List<Course> courseList = courseRequest.getCourseList();
	    for (Course item : courseList) {
	        if (!StringUtils.hasText(item.getCourseName()) || !StringUtils.hasText(item.getCourseNumber())
	                || !StringUtils.hasText(item.getSchoolDay()) || Objects.isNull(item.getStartTime())
	                || Objects.isNull(item.getEndTime())) {
	            return new CourseResponse("課程代碼跟課程名稱不得為空白");
	        }
	        if (item.getCredit() < 1 || item.getCredit() > 3) {
	            return new CourseResponse("輸入課程學分為1~3");
	        }
	        
	        // 確認課程代碼是否已存在於資料庫中
	        if (!courseDao.existsById(item.getCourseNumber())) {
	            // 若不存在，回傳錯誤訊息
	            errorCourseList.add(item);
	        } else {
	            // 若存在，則進行修改
	            Course courseToUpdate = new Course(item.getCourseNumber(), item.getCourseName(),
	            		item.getSchoolDay(), item.getStartTime(), item.getEndTime(),
	            		item.getCredit());
	            updatedCourseList.add(courseToUpdate);
	        }
	    }

	    if (!errorCourseList.isEmpty()) {
	        return new CourseResponse("課程代碼不存在");
	    }

	    courseDao.saveAll(updatedCourseList);
	    return new CourseResponse(updatedCourseList, "修改課程成功");
	}
}
//@Override
//public CourseResponse addCourse(CourseRequest courseRequest) {
//
//	// 新增一個空的課程清單，用來存放已存在的課程
//	List<Course> errorCourseList = new ArrayList<>();
//
//	// 從請求中取得待新增的課程清單
//	List<Course> courseList = courseRequest.getCourseList();
//
//	// 遍歷課程列表
//	for (Course item : courseList) {
//		// 確認每個課程代碼及課程名稱都不為空白
//		if (!StringUtils.hasText(item.getCourseName()) || !StringUtils.hasText(item.getCourseNumber())||
//				!StringUtils.hasText(item.getSchoolDay())||Objects.isNull(item.getStartTime())||
//				Objects.isNull(item.getEndTime())) {
//			return new CourseResponse("課程代碼跟課程名稱不得為空白");
//		}
//		if(item.getCredit()<1||item.getCredit()>3) {
//			return new CourseResponse("輸入課程學分為1~3");
//		}
//			
//		// 確認課程代碼是否已存在於資料庫中
//		if (courseDao.existsById(item.getCourseNumber())) {
//			// 若已存在，則將該課程加入已存在的清單中
//			errorCourseList.add(item);
//		}
//	}
//
//	if (!errorCourseList.isEmpty()) {
//		// 若已存在的清單不為空，表示有課程代碼已存在，回傳錯誤訊息
//		return new CourseResponse("課程代碼已存在");
//	}
//
//	// 若課程代碼都不存在，則一次性新增所有課程
//	courseDao.saveAll(courseList);
//
//	// 回傳新增成功訊息及新增的課程清單
//	return new CourseResponse(courseList, "新增課程成功");
//}
//
//@Override
//public CourseResponse deleteCourse(CourseRequest subjectSystemRequest) {
//    // 從請求中取得待刪除的課程清單
//    List<Course> deletecourseList = subjectSystemRequest.getCourseList();
//
//    // 遍歷課程列表
//    for (Course item : deletecourseList) {
//        // 確認每個課程代碼及課程名稱都不為空白
//        if (!StringUtils.hasText(item.getCourseNumber()) || !StringUtils.hasText(item.getCourseName())) {
//            // 若課程代碼或課程名稱為空白，則回傳錯誤訊息
//            return new CourseResponse("課程代碼跟課程名稱不得為空白");
//        }
//        // 確認課程代碼是否已存在於資料庫中
//        List<Course> errorCourseList = courseDao.findByCourseNumberAndCourseName(item.getCourseNumber(),
//                item.getCourseName());
//
//        if (errorCourseList.isEmpty()) {
//            // 若課程代碼不存在，則回傳錯誤訊息
//            return new CourseResponse("課程不存在");
//        }
//        // 確認該課程是否有學生選修，若有則回傳錯誤訊息
//        List<SelectCourse> courseNum = selectCourseDao.findByCourseNumber(item.getCourseNumber());
//        for (SelectCourse course : courseNum) {
//            if (selectCourseDao.existsByCourseNumber(course.getCourseNumber())) {
//                return new CourseResponse("該課程有學生選修無法刪除");
//            }
//        }
//        // 確認課程代碼及課程名稱是否存在於資料庫中，若不存在則回傳錯誤訊息
//        List<SelectCourse> courseNumAndName = selectCourseDao.findByCourseNumberAndCourseName(
//                item.getCourseNumber(),item.getCourseName());
//        if (!courseNumAndName.isEmpty()) {
//            return new CourseResponse("課程不存在2");
//        }
//        // 建立要刪除的課程清單
//        List<Course> newCourseList = new ArrayList<>();
//        for (Course courseItem : errorCourseList) {
//            // 將課程清單加入要刪除的課程清單中
//            Course deleteSelectCourse = new Course(item.getCourseNumber(),courseItem.getCourseName(),
//                    courseItem.getSchoolDay(),courseItem.getStartTime(),
//                    courseItem.getEndTime(),courseItem.getCredit());
//            newCourseList.add(deleteSelectCourse);
//        }
//        // 刪除課程
//        courseDao.deleteAll(newCourseList);
//        // 回傳刪除成功訊息及刪除的課程清單
//        return new CourseResponse(newCourseList, "刪除課程成功");
//    }
//    // 若無法刪除課程，則回傳錯誤訊息
//    return new CourseResponse("刪除課程失敗");
//}
//
//	@Override
//	public CourseResponse getCourseByCourseNumber(CourseRequest courseRequest) {
//		// 從 SubjectSystemRequest 取得要查詢的課程代碼列表
//		List<Course> courseList = courseRequest.getCourseList();
//
//		// 遍歷每一筆課程資料
//		for (Course item : courseList) {
//			String courseItem = item.getCourseNumber();
//			// 檢查單筆資料(item)，課程代碼不得為空
//			if (!StringUtils.hasText(item.getCourseNumber())) {
//				return new CourseResponse("課程代碼不得為空");
//			}
//			// 根據課程代碼查詢資料庫，回傳 Optional 類型的資料
//			Optional<Course> courseOpt = courseDao.findById(courseItem);
//			// 如果查詢的 Optional 為空，代表該課程代碼不存在
//			if (!courseOpt.isPresent()) {
//				return new CourseResponse("課程代碼不存在");
//			}
//			// 取得查詢到的課程資料
//			Course historysubject = courseOpt.get();
//			// 回傳該筆課程資料以及訊息，表示課程資料取得成功
//			return new CourseResponse(historysubject, "課程資料取得成功");
//		}
//		// 如果遍歷完所有課程資料都未回傳資料，代表課程資料取得失敗
//		return new CourseResponse("課程資料取得失敗");
//	}
//
//	@Override
//	public CourseResponse getCourseByCourseName(CourseRequest subjectSystemRequest) {
//		// 從 SubjectSystemRequest 取得要查詢的課程名稱列表
//		List<Course> courseList = subjectSystemRequest.getCourseList();
//		// 建立一個結果列表
//		List<Course> resultList = new ArrayList<>();
//
//		// 依序處理每個課程名稱
//		for (Course item : courseList) {
//			String subjectItem = item.getCourseName();
//			// 檢查課程名稱是否為空
//			if (!StringUtils.hasText(item.getCourseName())) {
//				return new CourseResponse("課程名稱不得為空");
//			}
//			// 以課程名稱查詢所有符合條件的課程
//			List<Course> course = courseDao.findAllByCourseName(subjectItem);
//			// 如果查詢結果為空，代表該課程名稱不存在，回傳錯誤訊息
//			if (course.isEmpty()) {
//				return new CourseResponse("課程名稱不存在");
//			} else {
//				// 如果查詢結果不為空，將所有查詢結果加入結果列表
//				resultList.addAll(course);
//			}
//		}
//		// 如果結果列表為空，代表所有課程名稱都不存在，回傳錯誤訊息
//		if (resultList.isEmpty()) {
//			return new CourseResponse("課程資料取得失敗");
//		}
//		// 如果結果列表不為空，代表有查詢到資料，回傳成功訊息及查詢結果
//		return new CourseResponse(resultList, "課程資料取得成功");
//	}
//
//}


//  86~87 這邊的寫法很對，上面那些你先存進去errorList後再判斷List有沒有東西的做法，其實都該改成這樣
  
