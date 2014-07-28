package com.pairwinter.snow.course.dao;

import com.pairwinter.snow.base.dao.MongoBaseDao;
import com.pairwinter.snow.base.dao.SearchType;
import com.pairwinter.snow.course.column.ChapterColumn;
import com.pairwinter.snow.course.model.Chapter;
import com.pairwinter.snow.course.model.Chapter;
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
public class ChapterDao extends MongoBaseDao<Chapter>{
    private static final Logger logger	= LoggerFactory.getLogger(ChapterDao.class);

    public DataPage<Chapter> searchChapters(Chapter chapter,Integer pageNo, Integer pageSize,List<OrderBy> orderByList) throws Exception {
        Criteria criteria = null;
        if(chapter.getName() != null){
            criteria = SearchType.LIKE.createQuery(ChapterColumn.NAME.getName(),chapter.getName());
        }
        return this.pagedQuery(new Query(criteria),pageNo,pageSize,orderByList);
    }
}
