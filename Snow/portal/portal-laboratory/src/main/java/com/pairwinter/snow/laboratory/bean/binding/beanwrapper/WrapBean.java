package com.pairwinter.snow.laboratory.bean.binding.beanwrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 对Bean进行包装，然后使用包装对象对原始对象的属性进行设置。
 * Created by damon on 9/18/14.
 */
public class WrapBean {
    public BeanWrapper beanWrapper;

    public void operateBeanName(BeanModel beanModel){
        beanWrapper = new BeanWrapperImpl(beanModel);
        beanWrapper.setPropertyValue("name","The name supplied by BeanWrapper");
    }

    public void operateBeanNestedBeanName(BeanModel beanModel){
        beanWrapper = new BeanWrapperImpl(beanModel);
        beanWrapper.setPropertyValue("anotherBeanModel.name","The anotherBeanModel.name was supplied by BeanWrapper");
    }

    public BeanModel getOriginalObjectFromWrapper(BeanModel beanModel){
        beanWrapper = new BeanWrapperImpl(beanModel);
        return (BeanModel)beanWrapper.getWrappedInstance();
    }
}
