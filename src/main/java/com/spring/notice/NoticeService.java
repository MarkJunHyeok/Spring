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

    //페이지 객체 받아오기
    public PageDTO pageGET(HttpServletRequest req){
        // 전체리스트 개수
        int listCnt = noticeDAO.noticePageCount();

        //현재 페이지
        int curPage = 1; //기본값
        if ( req.getParameter("num") != null){
            curPage = Integer.parseInt(req.getParameter("num"));
        }

        PageDTO page = new PageDTO(listCnt, curPage);

        return page;
    }


    public List<NoticeDTO> noticePageLoad(PageDTO page){
        List<NoticeDTO> notices = noticeDAO.noticeLoad(page);
        return notices;
    }

    public NoticeDTO noticeView(HttpServletRequest req){
        int num = Integer.parseInt(req.getParameter("ni_num"));
        noticeDAO.viewsUpdate(num);
        NoticeDTO noticeDTO = noticeDAO.noticeView(num);
        return noticeDTO;
    }

    public int noticeCreate(HttpServletRequest req , NoticeDTO noticeDTO){
        MemberDTO memberDTO = (MemberDTO) req.getSession().getAttribute("loginMember");
        noticeDTO.setMi_id(memberDTO.getMi_id());
        int result = noticeDAO.noticeCreate(noticeDTO);
        return result;
    }

    public int noticeUpdate(HttpServletRequest req , NoticeDTO noticeDTO){
        NoticeDTO sessionDTO = (NoticeDTO) req.getSession().getAttribute("noticeView");
        noticeDTO.setNi_num(sessionDTO.getNi_num());
        int result = noticeDAO.noticeUpdate(noticeDTO);
        return result;
    }

    public int noticeDelete(HttpServletRequest req){
        int num = Integer.parseInt(req.getParameter("ni_num"));
        int result = noticeDAO.noticeDelete(num);
        return result;
    }
}
