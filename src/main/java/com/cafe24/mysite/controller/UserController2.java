package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@SessionAttributes("authUser") // 세션 어노테이션은 타입에만 지정 가능. "authUser"가 세션에 들어가있는 에는 아님
@RequestMapping("/user2")
public class UserController2 {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login2";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute UserVo vo, Model model) {
        /*
         * Model을 통해서 session을 넣는것이고 
         * 모델 안에 authUser라는 이름으로 들어가면 session에 넣는다.
         */
        UserVo authUser = userService.getUser(vo);
        model.addAttribute("authUser", authUser);
        return "redirect:/main";
    }
    
    @ResponseBody
    @RequestMapping("/modify")
    public String modify(
            // @SessionAttributes와 똑같은 이름을 가지고 있다면 session에 있는 값을 넣는다.
            @ModelAttribute("authUser") UserVo authUser) {
        System.out.println(authUser);
        return "redirect:/modify";
    }
    
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/user2/login";
    }
}
