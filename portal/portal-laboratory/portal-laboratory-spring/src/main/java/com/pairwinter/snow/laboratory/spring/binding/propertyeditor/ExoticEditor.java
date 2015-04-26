package com.pairwinter.snow.laboratory.spring.binding.propertyeditor;

import java.beans.PropertyEditorSupport;

/**
 * PropertyEditor
 * Created by damon on 9/18/14.
 */
public class ExoticEditor  extends PropertyEditorSupport{
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(new ExoticItem(text.toUpperCase()));
    }
}
