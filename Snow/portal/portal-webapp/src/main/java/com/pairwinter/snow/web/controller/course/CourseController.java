package com.pairwinter.snow.web.controller.course;

import com.pairwinter.snow.course.model.Course;
import com.pairwinter.snow.course.service.CourseService;
import com.pairwinter.snow.exception.BaseException;
import com.pairwinter.snow.utils.SnowUtils;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import com.pairwinter.snow.utils.json.JsonUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by damon on 9/2/14.
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/addCourse", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addCourse(@RequestParam(value = "course", required = true) String courseStr) throws Exception{
        Course course = JsonUtils.convertToBean(Course.class,courseStr);
        courseService.addCourse(course);
        return course.getId().toString(10);
    }

    @RequestMapping(value="/modifyCourse", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String modifyCourse(@RequestParam(value = "course", required = true) String courseStr) throws Exception{
        Course newCourse = JsonUtils.convertToBean(Course.class, courseStr);
        courseService.modifyCourse(newCourse);
        return newCourse.getId().toString();
    }

    @RequestMapping(value="/removeCourses", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String removeCourses(@RequestParam(value = "courseIds", required = true) String courseIds) throws Exception{
        BigInteger[] ids = JsonUtils.convertToBean(BigInteger[].class,courseIds);
        List<BigInteger> idList = new ArrayList<BigInteger>();
        for (BigInteger id : ids) {
            idList.add(id);
        }
        courseService.removeCoursesByIds(idList);
        return ids.length+"";
    }

    /**
     * Get all courses with orderBy and sort direction without
     * @param pageNo
     * @param pageSize
     * @param orderBy column name
     * @param sort ARC or DESC
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fetchCourses", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String fetchCourses(Integer pageNo, Integer pageSize, String orderBy, String sort) throws Exception{
        DataPage courseDataPage = courseService.searchCourses(null,pageNo,pageSize, SnowUtils.buildOrderByList(orderBy,sort));
        return JsonUtils.toJson(courseDataPage,null, Calendar.getInstance().getTimeZone().getID());
    }

}
