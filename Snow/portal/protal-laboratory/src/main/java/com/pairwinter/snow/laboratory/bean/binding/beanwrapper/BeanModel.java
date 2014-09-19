package com.pairwinter.snow.laboratory.bean.binding.beanwrapper;

/**
 * Created by damon on 9/18/14.
 */
public class BeanModel {
    private String name;
    private AnotherBeanModel anotherBeanModel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnotherBeanModel getAnotherBeanModel() {
        return anotherBeanModel;
    }

    public void setAnotherBeanModel(AnotherBeanModel anotherBeanModel) {
        this.anotherBeanModel = anotherBeanModel;
    }
}
