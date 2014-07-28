package com.pairwinter.snow.course.impl;

import com.pairwinter.snow.base.dao.SearchType;
import com.pairwinter.snow.course.column.ChapterColumn;
import com.pairwinter.snow.course.dao.ChapterDao;
import com.pairwinter.snow.course.model.Chapter;
import com.pairwinter.snow.course.service.ChapterService;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damon on 14-7-14.
 */
public class ChapterServiceImpl implements ChapterService {
    private ChapterDao chapterDao;
    @Override
    public void addChapter(Chapter chapter) throws Exception {
        chapterDao.insert(chapter);
    }

    @Override
    public void addChapters(List<Chapter> chapters) throws Exception {
        if(!CollectionUtils.isEmpty(chapters)){
            chapterDao.insert(chapters);
        }
    }

    @Override
    public void removeChapter(Chapter chapter) throws Exception {
        chapterDao.deleteById(chapter.getId());
    }

    @Override
    public void removeChapter(BigInteger chapterId) throws Exception {
        chapterDao.deleteById(chapterId);
    }

    @Override
    public void modifyChapter(Chapter chapter) throws Exception {
        chapterDao.save(chapter);
    }

    @Override
    public Chapter fetchChapter(BigInteger chapterId) throws Exception {
        return chapterDao.getById(chapterId);
    }

    @Override
    public DataPage<Chapter> searchChapters(Chapter chapter,Integer pageNo, Integer pageSize,List<OrderBy> orderByList) throws Exception {
        return chapterDao.searchChapters(chapter, pageNo, pageSize, orderByList);
    }

    @Override
    public DataPage<Chapter> searchChaptersByName(String chapterName) throws Exception {
        Chapter chapter = new Chapter();
        chapter.setName(chapterName);
        return chapterDao.searchChapters(chapter,null,null,null);
    }

    @Override
    public boolean existChapterName(String chapterName) throws Exception {
        return this.fetchChaptersSizeByName(chapterName)>0;
    }

    @Override
    public long fetchChaptersSizeByName(String chapterName) throws Exception {
        Criteria criteria = SearchType.LIKE.createQuery(ChapterColumn.NAME.getName(),chapterName);
        return chapterDao.count(new Query(criteria));
    }

    public ChapterDao getChapterDao() {
        return chapterDao;
    }

    public void setChapterDao(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }
}
