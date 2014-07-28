package com.pairwinter.snow.course.impl;

import com.pairwinter.snow.base.column.BaseInfoColumn;
import com.pairwinter.snow.base.dao.SearchType;
import com.pairwinter.snow.course.column.CourseColumn;
import com.pairwinter.snow.course.dao.CourseDao;
import com.pairwinter.snow.course.model.Course;
import com.pairwinter.snow.course.service.CourseService;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by damon on 14-7-14.
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;
    @Override
    public void addCourse(Course course) throws Exception {
        courseDao.insert(course);
    }

    @Override
    public void addCourses(List<Course> courses) throws Exception {
        if(!CollectionUtils.isEmpty(courses)){
            courseDao.insert(courses);
        }
    }

    @Override
    public void removeCourse(Course course) throws Exception {
        courseDao.deleteById(course.getId());
    }

    @Override
    public void removeCourses(List<Course> courses) throws Exception {
        if(!CollectionUtils.isEmpty(courses)){
            List<BigInteger> ids = new ArrayList<BigInteger>();
            for (Course course : courses) {
                ids.add(course.getId());
            }
            this.removeCoursesByIds(ids);
        }
    }

    @Override
    public void removeCourseById(BigInteger courseId) throws Exception {
        courseDao.deleteById(courseId);
    }

    @Override
    public void removeCoursesByIds(List<BigInteger> ids) throws Exception {
        courseDao.deleteWithBatch(ids);
    }

    @Override
    public void modifyCourse(Course course) throws Exception {
        courseDao.save(course);
    }

    @Override
    public void modifyCourseName(BigInteger id,String newCourseName) throws Exception {
        Update update = new Update();
        update.set(CourseColumn.NAME.getName(),newCourseName);
        courseDao.updateFirst(new Query(SearchType.E.createQuery(BaseInfoColumn.id.getName(),id)),update);
    }

    @Override
    public Course fetchCourse(BigInteger courseId) throws Exception {
        return courseDao.getById(courseId);
    }

    @Override
    public DataPage<Course> searchCourses(Course course,Integer pageNo, Integer pageSize,List<OrderBy> orderByList) throws Exception {
        return courseDao.searchCourses(course, pageNo, pageSize, orderByList);
    }

    @Override
    public DataPage<Course> searchCoursesByName(String courseName) throws Exception {
        Course course = new Course();
        course.setName(courseName);
        return courseDao.searchCourses(course,null,null,null);
    }

    @Override
    public boolean existCourseName(String courseName) throws Exception {
        return this.fetchCoursesSizeByName(courseName)>0;
    }

    @Override
    public long fetchCoursesSizeByName(String courseName) throws Exception {
        Criteria criteria = SearchType.LIKE.createQuery(CourseColumn.NAME.getName(),courseName);
        return courseDao.count(new Query(criteria));
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
}
