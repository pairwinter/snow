package com.pairwinter.snow.web.handler;

import com.pairwinter.snow.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The function of this Hanlder is partly same with SnowHandlerExceptionResolver.
 * We can common precess the exception in Resolver.
 * Created by damon on 9/5/14.
 */
//@ControllerAdvice
public class SnowExceptionHandler {
    @ExceptionHandler
    public void handlerException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            ModelAndView mav = new ModelAndView();
            BaseException e = null;
            if(ex instanceof BaseException){
                response.setStatus(200);
                e = (BaseException)ex;
            }else{
                throw ex;
            }
            response.getWriter().write(e.toJSON());
        }else{
            response.setStatus(500);
        }
    }
}
