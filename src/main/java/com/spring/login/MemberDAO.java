package com.spring.login;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MemberDAO {

    @Autowired
    SqlSession sqlSession;

    private static final String NAMESPACE = "com.spring.login.MemberMapper";


    public int joinMember(HttpServletRequest req ,MemberDTO memberDTO){
        String address = req.getParameter("address")+"!"+req.getParameter("postcode")+"!"+req.getParameter("address2");
        memberDTO.setMi_addr(address);
        return sqlSession.getMapper(MemberMapper.class).joinMember(memberDTO);

    }

    public MemberDTO loadMember(MemberDTO memberDTO){
        return sqlSession.getMapper(MemberMapper.class).loadMember(memberDTO);
    }

    public int checkID(HttpServletRequest req){
        String id = req.getParameter("mi_id");
        return sqlSession.selectOne(NAMESPACE+".checkID", id);
    }

    public int updateMember(HttpServletRequest req, MemberDTO memberDTO) {
        String address = req.getParameter("address")+"!"+req.getParameter("postcode")+"!"+req.getParameter("address2");
        memberDTO.setMi_addr(address);
        return sqlSession.update(NAMESPACE+".updateMember", memberDTO);
    }

    public int deleteMember(HttpServletRequest req) {
        String id = req.getParameter("mi_id");
        return sqlSession.delete(NAMESPACE+".deleteMember", id);
    }
}
