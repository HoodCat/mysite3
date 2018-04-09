package com.cafe24.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestBookService;
import com.cafe24.mysite.vo.GuestBookVo;

@Controller("gusetBookAPIController")
@RequestMapping("/api/guestbook")
public class GuestBookController {
    
    @Autowired
    private GuestBookService guestBookService;
    
    @ResponseBody
    @RequestMapping("/list")
    public JSONResult list(
            @RequestParam(value="", required=true, defaultValue="0") Long no) {
        List<GuestBookVo> list = guestBookService.getMessageList(no);
        return JSONResult.success(list);
    }
    
    @ResponseBody
    @RequestMapping("/insert")
    public JSONResult insert(@RequestBody GuestBookVo vo) {
        GuestBookVo guestBookVo = guestBookService.insertMessage(vo);
        return JSONResult.success(guestBookVo);
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public JSONResult delete(@ModelAttribute GuestBookVo vo) {
        boolean result = guestBookService.delete(vo);
        return JSONResult.success(result?vo.getNo():-1);
    }
}
