package com.spring.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {

    @RequestMapping (value ="/notice")
    public String noticeGo(HttpServletRequest req){
        req.setAttribute("content", "notice/notice.jsp");
        return "home";
    }
}
