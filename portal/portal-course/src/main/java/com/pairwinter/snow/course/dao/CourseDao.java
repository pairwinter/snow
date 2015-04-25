package com.pairwinter.snow.course.dao;

import com.pairwinter.snow.base.dao.MongoBaseDao;
import com.pairwinter.snow.base.dao.SearchType;
import com.pairwinter.snow.course.column.CourseColumn;
import com.pairwinter.snow.course.model.Course;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by damon on 14-7-15.
 */
public class CourseDao extends MongoBaseDao<Course>{
    private static final Logger logger	= LoggerFactory.getLogger(CourseDao.class);

    public DataPage<Course> searchCourses(Course course,Integer pageNo, Integer pageSize,List<OrderBy> orderByList) throws Exception {
        Criteria criteria = new Criteria();
        if(course != null){
            if(course.getName() != null){
                criteria = SearchType.LIKE.createQuery(CourseColumn.NAME.getName(),course.getName());
            }
        }

        return this.pagedQuery(new Query(criteria),pageNo,pageSize,orderByList);
    }
}
