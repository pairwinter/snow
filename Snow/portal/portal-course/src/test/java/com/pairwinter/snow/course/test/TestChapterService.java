package com.pairwinter.snow.course.test;

import com.pairwinter.snow.base.enumerably.TimeZoneSnow;
import com.pairwinter.snow.course.impl.ChapterServiceImpl;
import com.pairwinter.snow.course.impl.CourseServiceImpl;
import com.pairwinter.snow.course.model.Chapter;
import com.pairwinter.snow.course.service.ChapterService;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import junit.framework.Assert;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
public class TestChapterService{
    protected static ApplicationContext applicationContext;
    protected static ChapterServiceImpl chapterService;
    static
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-course.xml");
        chapterService = (ChapterServiceImpl) applicationContext.getBean("chapterService");
    }

    private BigInteger courseId = new BigInteger("540982bfe4b0de6ee99e744f",16);

    @Test
    public void testChapterInsertBatch() throws Exception{
        List<Chapter> chapterList = new ArrayList<Chapter>();
        long insertSize = 2l;
        for (int i=0;i<insertSize;i++) {
            Chapter chapter = new Chapter();
            chapter.setCourseId(courseId);
            chapter.buildCreateBaseInfo();
            chapter.setName("TestChapter-" + Math.random());
            chapterList.add(chapter);
        }
        chapterService.addChapters(chapterList);
        Assert.assertEquals(chapterService.getChapterDao().countAll(),insertSize);
    }

    @Test
    public void addChapter(/*Chapter chapter*/) throws Exception {
        Chapter chapter = new Chapter();
        chapter.setCourseId(courseId);
        chapter.setName("test addChapter service");
        chapter.buildCreateBaseInfo();
        chapterService.addChapter(chapter);
    }

    @Test
    public void addChapters(/*List<Chapter> chapters*/) throws Exception {

    }

    @Test
    public void removeChapter(/*Chapter chapter*/) throws Exception {

    }

    @Test
    public void removeChapterById(/*BigInteger chapterId*/) throws Exception {

    }

    @Test
    public void modifyChapter(/*Chapter chapter*/) throws Exception {

    }

    @Test
    public Chapter fetchChapter(/*BigInteger chapterId*/) throws Exception {
        return null;
    }

    @Test
    public DataPage<Chapter> searchChapters(/*Chapter chapter, Integer pageNumber, Integer pageSize, List<OrderBy> orderByList*/) throws Exception {
        return null;
    }

    @Test
    public long fetchChaptersSizeByName(/*String chapterName*/) throws Exception {
        return 0;
    }

    @Test
    public DataPage<Chapter> searchChaptersByName(/*String chapterName*/) throws Exception {
        return null;
    }

    @Test
    public boolean existChapterName(/*String chapterName*/) throws Exception {
        return false;
    }
}
