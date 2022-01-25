package com.spring.notice;


import com.spring.PageComponent;
import com.spring.login.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    @Resource(name = "NoticeDAO")
    NoticeDAO noticeDAO;

    @Autowired
    PageComponent pageComponent;


    public Map<String, Object> noticePageLoad(HttpServletRequest req){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            String inputSearch = req.getParameter("inputSearch");
            String selectSearch = req.getParameter("selectSearch");
            int listCnt = noticeDAO.noticePageCount(inputSearch, selectSearch);
            int pagesize = Integer.parseInt(req.getParameter("pageSize"));
            int rangesize = Integer.parseInt(req.getParameter("rangeSize"));
            int curPage = Integer.parseInt(req.getParameter("curPage"));



            Map<String, Object> param = pageComponent.setPage(listCnt, curPage, pagesize, rangesize
                            ,inputSearch, selectSearch);
            List<NoticeDTO> list = noticeDAO.noticeLoad(param);
            result.put("param", param);
            result.put("list", list);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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
