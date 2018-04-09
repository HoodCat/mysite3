package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

    // 핸들러로 가기 전에 실행
    // false가 반환되면 핸들러고 안감
    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object arg2) throws Exception {
        System.out.println("MyInterceptor:preHandler");
        return true;
    }
    

    // 핸들러 함수가 끝나면 호출이 됨
    @Override
    public void postHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("MyInterceptor:postHandler");
    }

    // view까지 처리가 다 끝난 다음에 호출
    @Override
    public void afterCompletion(
            HttpServletRequest arg0, 
            HttpServletResponse arg1, 
            Object arg2, Exception arg3)
            throws Exception {
        System.out.println("MyInterceptor:afterCompletion");
    }
    
}
