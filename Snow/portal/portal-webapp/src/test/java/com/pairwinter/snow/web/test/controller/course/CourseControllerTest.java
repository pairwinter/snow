package com.pairwinter.snow.web.test.controller.course;

import com.pairwinter.snow.base.column.BaseInfoColumn;
import com.pairwinter.snow.base.model.BaseInfo;
import com.pairwinter.snow.course.model.Course;
import com.pairwinter.snow.course.service.CourseService;
import com.pairwinter.snow.utils.datapage.OrderBy;
import com.pairwinter.snow.utils.json.JsonUtils;
import com.pairwinter.snow.web.controller.course.CourseController;
import com.pairwinter.snow.web.test.AbstractControllerTest;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by damon on 9/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseControllerTest extends AbstractControllerTest
{
    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(this.wac).alwaysDo(print()).build();
    }

    @Test
    public void addCourse() throws Exception{
        Course course = new Course();
        course.setName("JavaScript"+(System.currentTimeMillis()));
        String courseJson = JsonUtils.toJson(course);
        this.mockMvc.perform(post("/course/addCourse")
                .param("course", courseJson)
                .header("X-Requested-With","XMLHttpRequest")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    public void modifyCourse() throws Exception{
        Course course = new Course();
        course.setId(new BigInteger("26014261042484243676596044666"));
        course.setName("Modified_Course_Name");
        String courseJson = JsonUtils.toJson(course);
        this.mockMvc.perform(
                put("/course/modifyCourse")
                .param("course", courseJson)
                .header("X-Requested-With","XMLHttpRequest")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("26014261042484243676596044666"))
//                .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    public void deleteCourses() throws Exception{
        String[] ids = new String[]{"26014261042484243676596044666","26016077142885027031673637001"};
        String idsStr = JsonUtils.toJson(ids);
        this.mockMvc.perform(
                delete("/course/removeCourses")
                .param("courseIds", idsStr)
                .header("X-Requested-With","XMLHttpRequest")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(ids.length+""))
//                .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    public void fetchCourses() throws Exception{
        this.mockMvc.perform(
            post("/course/fetchCourses")
                .param("pageSize","2")
                .param("pageNo","1")
                .param("orderBy", BaseInfoColumn.name.getName())
                .param("sort", OrderBy.OrderDirection.DESC.name())
                .header("X-Requested-With","XMLHttpRequest")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageSize").exists())
//                .andExpect(jsonPath("$.pageSize").value(1))
                .andExpect(jsonPath("$.data").isArray())
//                .andExpect(jsonPath("$.data[0].name").value("JavaScript1409813349328"))
        ;
    }
}
