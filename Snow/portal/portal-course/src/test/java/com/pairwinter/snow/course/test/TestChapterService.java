package com.pairwinter.snow.course.test;

import com.pairwinter.snow.base.enumerably.TimeZoneSnow;
import com.pairwinter.snow.course.impl.ChapterServiceImpl;
import com.pairwinter.snow.course.impl.CourseServiceImpl;
import com.pairwinter.snow.course.model.Chapter;
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
public class TestChapterService {
    protected static ApplicationContext applicationContext;
    protected static ChapterServiceImpl chapterService;
    static
    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-course.xml");
        chapterService = (ChapterServiceImpl) applicationContext.getBean("chapterService");
    }

    @Test
    public void testChapterInsertBatch() throws Exception{
        List<Chapter> chapterList = new ArrayList<Chapter>();
        for (int i=0;i<10000;i++) {
            Chapter chapter = new Chapter();
            chapter.buildCreateBaseInfo().buildModifiedBaseInfo();
            chapter.setName("Test-" + Math.random());
            chapterList.add(chapter);
        }
        long start = System.currentTimeMillis();
        chapterService.addChapters(chapterList);
        long usedTime = System.currentTimeMillis()-start;
        System.out.println("used time(ms) : " + usedTime);

        chapterService.getChapterDao().deleteAll();
    }
}
