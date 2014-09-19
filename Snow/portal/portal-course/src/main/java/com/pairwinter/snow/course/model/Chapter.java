package com.pairwinter.snow.course.model;

import com.pairwinter.snow.base.model.BaseInfo;

import java.math.BigInteger;

/**
 * Created by damon on 14-7-14.
 */
public class Chapter extends BaseInfo {

    private String name;
    private BigInteger courseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }
}
