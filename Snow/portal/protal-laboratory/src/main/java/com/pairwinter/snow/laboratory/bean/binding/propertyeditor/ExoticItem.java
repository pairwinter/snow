package com.pairwinter.snow.laboratory.bean.binding.propertyeditor;

/**
 * Created by damon on 9/18/14.
 */
public class ExoticItem {
    private String name;
    public ExoticItem() { }

    public ExoticItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}