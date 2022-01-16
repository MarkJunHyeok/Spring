package com.spring.notice;

import com.spring.login.MemberDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NoticeService {

    @Resource(name = "NoticeDAO")
    NoticeDAO noticeDAO;

    public void noticePageLoad(HttpServletRequest req){
        // 전체리스트 개수
        int listCnt = noticeDAO.noticePageCount();

        //현재 페이지
        int curPage = 1; //기본값
        if ( req.getParameter("num") != null){
            curPage = Integer.parseInt(req.getParameter("num"));
        }

        PageDTO page = new PageDTO(listCnt, curPage);
        List<NoticeDTO> notices = noticeDAO.noticeLoad(page);
        req.setAttribute("notices", notices);
        req.setAttribute("page" , page);
        req.setAttribute("content", "notice/notice.jsp");
    }

    public void noticeView(HttpServletRequest req){
        int num = Integer.parseInt(req.getParameter("ni_num"));
        noticeDAO.viewsUpdate(num);
        NoticeDTO noticeDTO = noticeDAO.noticeView(num);

        if( noticeDTO == null){
            System.out.println("실패");
        }else{
            req.getSession().setAttribute("noticeView", noticeDTO);
            req.setAttribute("content", "notice/view.jsp");
        }
    }

    public void noticeCreate(HttpServletRequest req , NoticeDTO noticeDTO){
        MemberDTO memberDTO = (MemberDTO) req.getSession().getAttribute("loginMember");
        noticeDTO.setMi_id(memberDTO.getMi_id());
        int result = noticeDAO.noticeCreate(noticeDTO);
        if(result == 0){
            req.setAttribute("MSG" , "게시글 작성에 실패하였습니다.");
        }else{
            req.setAttribute("MSG", "게시글 작성에 성공하셨습니다.");
        }
    }

    public void noticeUpdate(HttpServletRequest req , NoticeDTO noticeDTO){
        NoticeDTO sessionDTO = (NoticeDTO) req.getSession().getAttribute("noticeView");
        noticeDTO.setNi_num(sessionDTO.getNi_num());
        int result = noticeDAO.noticeUpdate(noticeDTO);

        if(result == 0){
            req.setAttribute("MSG" , "게시글 수정이 실패되었습니다.");
        }else{
            req.setAttribute("MSG" , "게시글 수정이 완료되었습니다.");
        }
    }

    public void noticeDelete(HttpServletRequest req){
        int num = Integer.parseInt(req.getParameter("ni_num"));
        int result = noticeDAO.noticeDelete(num);

        if( result == 0){
            req.setAttribute("MSG" , "게시글 삭제가 실패되었습니다.");
        }else{
            req.setAttribute("MSG" , "게시글 삭제가 완료되었습니다.");
        }
    }
}
