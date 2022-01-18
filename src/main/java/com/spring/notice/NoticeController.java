package com.spring.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NoticeController {


    @Autowired
    NoticeService noticeService;


    //게시판 이동
    @RequestMapping (value ="/notice")
    public String noticeGo(HttpServletRequest req){
        noticeLoad(req);
        return "home";
    }


    //게시글 상세보기
    @RequestMapping (value = "/noticeView")
    public String noticeView(HttpServletRequest req){
        NoticeDTO noticeDTO = noticeService.noticeView(req);
        if( noticeDTO == null){
            System.out.println("실패");
        }else{
            req.getSession().setAttribute("noticeView", noticeDTO);
            req.setAttribute("content", "notice/view.jsp");
        }
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
        int result = noticeService.noticeCreate(req, noticeDTO);
        if(result == 0){
            req.setAttribute("MSG" , "게시글 작성에 실패하였습니다.");
        }else{
            req.setAttribute("MSG", "게시글 작성에 성공하셨습니다.");
        }
        noticeLoad(req);
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
        int result = noticeService.noticeUpdate(req,noticeDTO);
        if(result == 0){
            req.setAttribute("MSG" , "게시글 수정이 실패되었습니다.");
        }else{
            req.setAttribute("MSG" , "게시글 수정이 완료되었습니다.");
        }
        noticeLoad(req);
        return "home";
    }

    //게시글 삭제
    @RequestMapping (value = "/noticeDelete")
    public String noticeDelete(HttpServletRequest req){
        int result =  noticeService.noticeDelete(req);
        if( result == 0){
            req.setAttribute("MSG" , "게시글 삭제가 실패되었습니다.");
        }else{
            req.setAttribute("MSG" , "게시글 삭제가 완료되었습니다.");
        }
        noticeLoad(req);
        return "home";
    }


    //페이지 로드
    private void noticeLoad(HttpServletRequest req){
        PageDTO page = noticeService.pageGET(req);
        List<NoticeDTO> notices = noticeService.noticePageLoad(page);
        req.setAttribute("notices", notices);
        req.setAttribute("page" , page);
        req.setAttribute("content", "notice/notice.jsp");
    }








}
