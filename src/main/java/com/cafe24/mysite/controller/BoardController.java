package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(value = "page", required = true, defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("page", page);
        parameter.put("keyword", keyword);
        model.addAttribute("boardList", boardService.getList(parameter));
        model.addAttribute("keyword", keyword);
        model.addAttribute("curPage", page);
        model.addAttribute("endPage", boardService.getEndPage(keyword));
        model.addAttribute("totCount", boardService.getTotCount(keyword));
        return "board/list";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String list(Model model,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return "redirect:/board?page=1&keyword="+keyword;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(Model model) {
        model.addAttribute("isReply", false);
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@ModelAttribute BoardVo vo) {
        boardService.write(vo);
        return "redirect:/board";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "no", required = true) int no,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "page", required = false, defaultValue = "") String keyword,
            Model model) {

        model.addAttribute("vo", boardService.getBoard(no));
        model.addAttribute("cmtList", boardService.getCmtList(no));
        boardService.addViews(no);
        return "board/view";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam(value = "no", required = true) int no,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        model.addAttribute("vo", boardService.getBoard(no));
        return "board/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@ModelAttribute BoardVo vo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        boardService.modify(vo);
        return "redirect:/board/view?no=" + vo.getNo() + "&page=" + page;
    }

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public String reply(Model model) {
        model.addAttribute("isReply", true);
        return "board/write";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String reply(
            @ModelAttribute BoardVo vo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        boardService.reply(vo);
        return "redirect:/board?page=" + page;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpSession session, @RequestParam(value = "no", required = true) int no,
            @RequestParam(value = "userNo", required = true) int userNo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser != null && authUser.getNo() == userNo) {
            boardService.delete(no);
        }
        return "redirect:/board?page=" + page + "&keyword=" + keyword;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String comment(@ModelAttribute CommentVo vo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        boardService.comment(vo);
        return "redirect:/board/view?no="+vo.getBoardNo()+"&page="+page;
    }
    
    @RequestMapping(value = "/commentdelete", method = RequestMethod.GET)
    public String commentDelete(
            @RequestParam(value = "no", required = true) int boardNo,
            @RequestParam(value = "commentNo", required = true) int commentNo,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        boardService.commentDelete(commentNo);
        return "redirect:/board/view?no="+boardNo+"&page="+page + "&keyword=" + keyword;
    }
}
