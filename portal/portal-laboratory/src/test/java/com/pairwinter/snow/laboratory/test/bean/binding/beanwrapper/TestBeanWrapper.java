package com.pairwinter.snow.laboratory.test.bean.binding.beanwrapper;

import com.pairwinter.snow.laboratory.bean.binding.beanwrapper.AnotherBeanModel;
import com.pairwinter.snow.laboratory.bean.binding.beanwrapper.BeanModel;
import com.pairwinter.snow.laboratory.bean.binding.beanwrapper.WrapBean;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by damon on 9/18/14.
 */
public class TestBeanWrapper{
    public WrapBean wrapBean = new WrapBean();
    @Test
    public void operateBeanName(/*BeanModel beanModel*/) {
        BeanModel beanModel = new BeanModel();
        wrapBean.operateBeanName(beanModel);
        Assert.assertEquals(beanModel.getName(),"The name supplied by BeanWrapper");
    }

    @Test
    public void operateBeanNestedBeanName(/*BeanModel beanModel*/) {
        BeanModel beanModel = new BeanModel();
        beanModel.setAnotherBeanModel(new AnotherBeanModel());
        wrapBean.operateBeanNestedBeanName(beanModel);
        Assert.assertEquals(beanModel.getAnotherBeanModel().getName(),"The anotherBeanModel.name was supplied by BeanWrapper");
    }

    @Test
    public void getOriginalObjectFromWrapper(/*BeanModel beanModel*/) {
        BeanModel beanModel = new BeanModel();
        wrapBean.getOriginalObjectFromWrapper(beanModel);
        Assert.assertEquals(true,beanModel == wrapBean.getOriginalObjectFromWrapper(beanModel));
    }
}
