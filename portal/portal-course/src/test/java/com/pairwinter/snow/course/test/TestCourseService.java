package com.pairwinter.snow.course.test;

import com.pairwinter.snow.base.enumerably.TimeZoneSnow;
import com.pairwinter.snow.course.dao.CourseDao;
import com.pairwinter.snow.course.impl.CourseServiceImpl;
import com.pairwinter.snow.course.model.Course;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by damon on 14-7-15.
 */
public class TestCourseService {
    protected static ApplicationContext applicationContext;
    protected static CourseServiceImpl courseService;
    protected static CourseDao courseDao;
    static
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-course.xml");
        courseService = (CourseServiceImpl) applicationContext.getBean("courseService");
        courseDao = courseService.getCourseDao();
    }

    @Test
    public void testDate() throws Exception{
        Date date = new Date();
        DateTime dateTime = new DateTime(date);
        System.out.println(dateTime.toString());
        System.out.println(dateTime.getZone().getID());
//        dateTime.withZone();
        dateTime.withZone(DateTimeZone.UTC);
        System.out.println(dateTime.toString());
        System.out.println(dateTime.getZone().getID());
        System.out.println(TimeZoneSnow.Asia_Shanghai.getID());
    }

    private long countAll(){
        return courseDao.countAll();
    }

    private Course buildCourse(){
        Course course = new Course();
        course.buildCreateBaseInfo().buildModifiedBaseInfo();
        course.setName("Test-Data-" + Math.random());
        return course;
    }

    private List<Course> buildCourseList(int size){
        List<Course> courseList = new ArrayList<Course>();
        for (int i=0;i<size;i++) {
            courseList.add(this.buildCourse());
        }
        return courseList;
    }

    private void removeAllTestData() throws Exception{
        List<Course> courseList = courseService.searchCoursesByName("Test-Data-").getData();
        courseService.removeCourses(courseList);
    }

    @Test
    public void testCourseInsert() throws Exception{
        this.removeAllTestData();
        courseService.addCourse(this.buildCourse());
        System.out.println("insert "+courseDao.countAll()+" item!");
        this.removeAllTestData();
    }

    @Test
    public void testCourseInsertBatch() throws Exception{
        List<Course> courseList = this.buildCourseList(1000);
        long start = System.currentTimeMillis();
        courseService.addCourses(courseList);
        long usedTime = System.currentTimeMillis()-start;
        System.out.println("used time(ms) : " + usedTime);
        System.out.println("inserted "+this.countAll()+" items!");
        this.removeAllTestData();
    }

    @Test
    public void testFetchCoursesSize() throws Exception{
        courseService.addCourse(this.buildCourse());
        System.out.println("The counts of items that contain \"Test\" --> " + courseService.fetchCoursesSizeByName("Test"));
        this.removeAllTestData();
    }

    @Test
    public void testRemoveCourse() throws Exception{
        this.removeAllTestData();
        courseService.addCourse(this.buildCourse());
        List<Course> courseList = courseService.searchCoursesByName("Test").getData();
        System.out.println("items count before delete --> "+courseService.fetchCoursesSizeByName("Test"));
        for (Course course : courseList) {
            courseService.removeCourse(course);
        }
        System.out.println("items count after delete --> "+courseService.fetchCoursesSizeByName("Test"));
    }

    @Test
    public void testRemoveCourseById() throws Exception{
        this.removeAllTestData();
        courseService.addCourse(this.buildCourse());
        List<Course> courseList = courseService.searchCoursesByName("Test").getData();
        System.out.println("items count before delete --> "+courseService.fetchCoursesSizeByName("Test"));
        for (Course course : courseList) {
            courseService.removeCourseById(course.getId());
        }
        System.out.println("items count after delete --> "+courseService.fetchCoursesSizeByName("Test"));
    }

    @Test
    public void testRemoveCoursesByIds() throws Exception{
        this.removeAllTestData();
        courseService.addCourses(this.buildCourseList(100));
        List<Course> courseList = courseService.searchCoursesByName("Test").getData();
        List<BigInteger> ids = new ArrayList<BigInteger>();
        for (Course course : courseList) {
            ids.add(course.getId());
        }
        System.out.println("items count before delete --> "+courseService.fetchCoursesSizeByName("Test"));
        courseService.removeCoursesByIds(ids);
        System.out.println("items count before delete --> "+courseService.fetchCoursesSizeByName("Test"));
    }

    @Test
    public void testModifyCourse() throws Exception{
        this.removeAllTestData();
        //add course
        Course newCourse = this.buildCourse();
        courseService.addCourse(newCourse);
        newCourse.toString();
        //fetch and modify and save it
        Course course = courseService.fetchCourse(newCourse.getId());
        course.toString();
        course.setName(course.getName()+"Modified");
        course.buildModifiedBaseInfo();
        courseService.modifyCourse(course);
        //refetch the modified course
        course = courseService.fetchCourse(course.getId());
        course.toString();
        courseService.removeCourse(course);
    }

    @Test
    public void testModifyCourseName() throws Exception{
        this.removeAllTestData();
        Course course = this.buildCourse();
        courseService.addCourse(course);
        //modify the name of course
        courseService.modifyCourseName(course.getId(),course.getName()+"Modified");
        course = courseService.fetchCourse(course.getId());
        Assert.assertEquals("The name of course should be end with Modified!",true,course.getName().endsWith("Modified"));
        this.removeAllTestData();
    }

}
