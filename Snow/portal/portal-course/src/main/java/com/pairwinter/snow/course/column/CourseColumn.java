package com.pairwinter.snow.course.column;

/**
 * Created by damon on 14-7-15.
 */
public enum CourseColumn {
    NAME("name");
    private String columnName;

    private CourseColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getName() {
        return columnName;
    }
}
