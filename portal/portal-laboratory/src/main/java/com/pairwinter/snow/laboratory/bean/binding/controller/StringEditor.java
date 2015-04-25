package com.pairwinter.snow.laboratory.bean.binding.controller;

import java.beans.PropertyEditorSupport;

/**
 * Created by damon on 9/19/14.
 */
public class StringEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(text.toUpperCase());
//        super.setAsText(text);

    }
}
