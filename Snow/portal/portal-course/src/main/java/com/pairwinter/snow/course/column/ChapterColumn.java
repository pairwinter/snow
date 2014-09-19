package com.pairwinter.snow.course.column;

/**
 * Created by damon on 14-7-15.
 */
public enum ChapterColumn {
    NAME("name"),
    COURSE_ID("courseId")
    ;
    private String columnName;

    private ChapterColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getName() {
        return columnName;
    }
}
