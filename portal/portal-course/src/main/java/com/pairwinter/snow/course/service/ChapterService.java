package com.pairwinter.snow.course.service;

import com.pairwinter.snow.course.model.Chapter;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by damon on 14-7-14.
 */
public interface ChapterService {
    public void addChapter(Chapter chapter) throws Exception;

    public void addChapters(List<Chapter> chapters) throws Exception;
    public void removeChapter(Chapter chapter) throws Exception;

    public void removeChapter(BigInteger chapterId) throws Exception;

    public void modifyChapter(Chapter chapter) throws Exception;

    public Chapter fetchChapter(BigInteger chapterId) throws Exception;

    public DataPage<Chapter> searchChapters(Chapter chapter, Integer pageNumber, Integer pageSize, List<OrderBy> orderByList) throws Exception;

    public long fetchChaptersSizeByName(String chapterName) throws Exception;

    public DataPage<Chapter> searchChaptersByName(String chapterName) throws Exception;

    public boolean existChapterName(String chapterName) throws Exception;

}
