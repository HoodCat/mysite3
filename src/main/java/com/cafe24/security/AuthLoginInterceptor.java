package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
    
    /*@Autowired
    private UserService userService;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        /* 다음과 같이 코드를 사용하면 안된다. 
         * 왜냐하면 Spring Container가 의존성을 주입시켜주지만
         * 새롭게 new를 이용하여 userService를 만드는 경우
         * UserDao를 생성해서 의존성을 넣어주어야 하지만
         * 현재 userService에는 UserDao를 넣어줄 세터가 없다.
         * 즉, RootApplicationContext에 만들어진 userService 빈을 사용하면 된다.
         */ 
        /*
         * UserService userService = new UserService();
         * UserVo vo = new UserVo();
         * vo.setEmail(email); 
         * vo.setPassword(password); 
         * UserVo authUser = userService.getUser(vo);
         */
        
        /*
         * 1. @Autowired를 사용하는 방법
         * 실제 Interceptor는 webApplicationContext에 들어있다.
         * 그러므로 RootApplicationContext에 있는 빈을 받아올 수 있다.
         * 
         * 2. 컨테이너에 접근하는 방법
         */
        
        ApplicationContext ac =  WebApplicationContextUtils
                .getWebApplicationContext(
                        request.getServletContext());
        
        UserService userService = ac.getBean(UserService.class);
        UserVo vo = new UserVo();
        vo.setEmail(email); 
        vo.setPassword(password);
        UserVo authUser = userService.getUser(vo);
        
        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        
        HttpSession session = request.getSession(true);
        session.setAttribute("authUser", authUser);
        response.sendRedirect(request.getContextPath() + "/main");
        return false;
    }

}
