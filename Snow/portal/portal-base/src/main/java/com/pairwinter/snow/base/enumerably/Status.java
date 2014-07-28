package com.pairwinter.snow.base.enumerably;

/**
 * Created by damon on 14-7-15.
 */
public enum Status {
    A("A"),D("D");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
