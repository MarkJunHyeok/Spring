package com.spring.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
@Controller
public class NoticeController {


    @Autowired
    NoticeService noticeService;


    //게시판 이동
    @RequestMapping (value ="/notice")
    public String noticeGo(HttpServletRequest req){
        noticeService.noticePageLoad(req);
        return "home";
    }


    //게시글 상세보기
    @RequestMapping (value = "/noticeView")
    public String noticeView(HttpServletRequest req){
        noticeService.noticeView(req);
        return "home";
    }

    //게시글 작성 시작
    @RequestMapping (value = "/noticeCreatePage")
    public String noticeCreatePage(HttpServletRequest req){
        req.setAttribute("content", "notice/create.jsp");
        return "home";
    }

    //게시글 작성 완료
    @RequestMapping (value = "/noticeCreate")
    public String noticeCreate(HttpServletRequest req , NoticeDTO noticeDTO){
        noticeService.noticeCreate(req, noticeDTO);
        noticeService.noticePageLoad(req);
        return "home";
    }

    //게시글 수정 시작
    @RequestMapping( value = "/noticeUpdatePage")
    public String noticeUpdatePage(HttpServletRequest req){
        req.setAttribute("content" , "notice/update.jsp");
        return "home";
    }

    //게시글 수정 완료
    @RequestMapping( value = "/noticeUpdate" , method = RequestMethod.POST)
    public String noticeUpdate(HttpServletRequest req , NoticeDTO noticeDTO){
        noticeService.noticeUpdate(req,noticeDTO);
        noticeService.noticePageLoad(req);
        return "home";
    }

    //게시글 삭제
    @RequestMapping (value = "/noticeDelete")
    public String noticeDelete(HttpServletRequest req){
        noticeService.noticeDelete(req);
        noticeService.noticePageLoad(req);
        return "home";
    }









}
