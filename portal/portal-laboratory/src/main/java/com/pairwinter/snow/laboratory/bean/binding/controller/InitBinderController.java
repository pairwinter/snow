package com.pairwinter.snow.laboratory.bean.binding.controller;

import com.pairwinter.snow.laboratory.bean.binding.propertyeditor.ExoticEditor;
import com.pairwinter.snow.laboratory.bean.binding.propertyeditor.ExoticItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by damon on 9/19/14.
 */
@Controller
@RequestMapping("/laboratory/initBinder")
public class InitBinderController {
    @RequestMapping(value = "/post",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String parsePost(@RequestParam(value = "str",required = true) String str,ExoticItem item){
        return item.getName();
    }

    @InitBinder
    public void initWebDataBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class,new StringEditor());
//        binder.registerCustomEditor(ExoticItem.class,new ExoticEditor());
//        binder.addValidators();
    }
}
