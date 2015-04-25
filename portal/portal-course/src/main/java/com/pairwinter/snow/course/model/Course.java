package com.pairwinter.snow.course.model;

import com.pairwinter.snow.base.model.BaseInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;

/**
 * Created by damon on 14-7-14.
 */
public class Course extends BaseInfo {

    private static final Logger logger = LoggerFactory.getLogger(Course.class);

    protected String name;
    private long index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        String result = String.format(" %nid:%d%n name:%s%n createdTime:%tc%n lastModifiedTime:%tc%n",this.getId(),this.getName(),this.getCreatedTime(),this.getLastModifiedTime());
        logger.debug(result);
        return result;
    }
}
