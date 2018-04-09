package com.cafe24.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute UserVo vo) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {
        
        if(result.hasErrors()) {
            /*
             * List<ObjectError> list = result.getAllErrors();
             * for(ObjectError error: list) {
             * System.out.println(error); 
             * } 
             * return "user/join";
             */
            return "user/join";
        }
        
        userService.join(vo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value = "/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @Auth // <- 접근제어를 위한 사용자 어노테이션
    @RequestMapping(value="/modify",method=RequestMethod.GET)
    public String modify(@AuthUser UserVo authUser, Model model
    /* @AuthUser UserVo authUser <- 세션 값을 사용하기 위한 사용자 어노테이션 */) {
        /* 접근 제어 */
//        UserVo authUser = (UserVo) session.getAttribute("authUser");
        System.out.println(authUser);
        
        UserVo vo = userService.getUser(authUser);
        if (authUser == null) {
            return "redirect:/main";
        }

        return "user/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@ModelAttribute UserVo vo) {
        userService.modify(vo);
        return "redirect:/main";
    }

    /*
     * @ExceptionHandler( UserDaoException.class ) public String
     * handleUserDaoException() { 로그 남기기 return "error/exception"; }
     */

}
