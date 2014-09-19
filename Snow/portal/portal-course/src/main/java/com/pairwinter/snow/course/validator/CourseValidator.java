package com.pairwinter.snow.course.validator;

import com.pairwinter.snow.course.model.Course;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by damon on 9/11/14.
 */
public class CourseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return this.getClass().equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.empty");
        Course course = (Course)target;
        if(course.getIndex()<0){
            errors.rejectValue("index","negativevale");
        }
    }
}
