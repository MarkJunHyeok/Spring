package com.spring.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {

    //게시판 이동
    @RequestMapping (value ="/notice")
    public String noticeGo(HttpServletRequest req){
        req.setAttribute("content", "notice/notice.jsp");
        return "home";
    }

    //글쓰기 시작.
    @RequestMapping (value = "/noticeText")
    public String noticeCreate(HttpServletRequest req){
        req.setAttribute("content", "notice/create.jsp");
        return "home";
    }



}
