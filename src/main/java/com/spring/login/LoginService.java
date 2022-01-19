package com.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {

    @Resource(name = "MemberDAO")
    MemberDAO memberDAO;


    public int joinMember(HttpServletRequest req, MemberDTO memberDTO){
        String address = req.getParameter("address")+"!"+req.getParameter("postcode")+"!"+req.getParameter("address2");
        memberDTO.setMi_addr(address);
        return memberDAO.joinMember(memberDTO);
    }

    public int updateMember(HttpServletRequest req, MemberDTO memberDTO){
        String address = req.getParameter("address")+"!"+req.getParameter("postcode")+"!"+req.getParameter("address2");
        memberDTO.setMi_addr(address);
        return memberDAO.updateMember(memberDTO);
    }

    public MemberDTO loadMember(MemberDTO memberDTO) {
        return memberDAO.loadMember(memberDTO);
    }

    public int checkID(HttpServletRequest req) {
        String id = req.getParameter("mi_id");
        return memberDAO.checkID(id);
    }

    public int deleteMember(HttpServletRequest req) {
        String id = req.getParameter("mi_id");
        return memberDAO.deleteMember(id);
    }
}
