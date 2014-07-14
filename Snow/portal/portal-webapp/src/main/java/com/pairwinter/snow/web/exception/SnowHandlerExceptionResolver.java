package com.pairwinter.snow.web.exception;

import com.pairwinter.snow.exception.BaseException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by damon on 14-7-11.
 */
public class SnowHandlerExceptionResolver extends SimpleMappingExceptionResolver implements HandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        try {
            ex.printStackTrace();
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.setStatus(200);
                ModelAndView mav = new ModelAndView();
                BaseException e = null;
                if(ex instanceof BaseException){
                    e = (BaseException)ex;
                }else{
                    e = new BaseException(500,null);
                }
                response.getWriter().write(e.toJSON());
                return mav;
            }else{
                return super.doResolveException(request,response,handler,ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        return null;
    }
}
