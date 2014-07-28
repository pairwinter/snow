package com.pairwinter.snow.base.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by damon on 14-7-14.
 */
public class BaseInfo implements Serializable {
    @Id
    protected BigInteger id;
    protected Date createdTime;
    protected Date lastModifiedTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public BaseInfo buildCreateBaseInfo(){
        this.createdTime = new Date();
        return this;
    }
    public BaseInfo buildModifiedBaseInfo(){
        this.lastModifiedTime = new Date();
        return this;
    }
}
