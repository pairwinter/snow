package com.pairwinter.snow.course.service;

import com.pairwinter.snow.course.model.Course;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by damon on 14-7-14.
 */
public interface CourseService {
    public void addCourse(Course course) throws Exception;

    public void addCourses(List<Course> courses) throws Exception;
    public void removeCourse(Course course) throws Exception;
    public void removeCourses(List<Course> courses) throws Exception;

    public void removeCourseById(BigInteger courseId) throws Exception;

    public void removeCoursesByIds(List<BigInteger> ids) throws Exception;

    public void modifyCourse(Course course) throws Exception;
    public void modifyCourseName(BigInteger id, String courseName) throws Exception;

    public Course fetchCourse(BigInteger courseId) throws Exception;

    public DataPage<Course> searchCourses(Course course,Integer pageNumber,Integer pageSize,List<OrderBy> orderByList) throws Exception;

    public long fetchCoursesSizeByName(String courseName) throws Exception;

    public DataPage<Course> searchCoursesByName(String courseName) throws Exception;

    public boolean existCourseName(String courseName) throws Exception;

}
