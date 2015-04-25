package com.pairwinter.snow.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by damon on 9/4/14.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping( value = "/getException",produces = "application/json")
    @ResponseBody
    public String getException() throws Exception{
        throw new Exception();
    }

    @RequestMapping( value = "/getNullException")
    @ResponseBody
    public String getNullException() throws Exception{
        throw new NullPointerException();
    }

    @ExceptionHandler
    public void localExceptionHandler(Exception e) throws Exception{
        throw e;
    }
}
