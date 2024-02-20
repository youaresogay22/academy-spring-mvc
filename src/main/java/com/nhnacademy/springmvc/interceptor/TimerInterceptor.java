package com.nhnacademy.springmvc.interceptor;

import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimerInterceptor implements HandlerInterceptor {
    StopWatch stopWatch = new StopWatch("controllertimer");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        stopWatch.start();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        stopWatch.stop();
        stopWatch.prettyPrint();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
