package com.spring.login;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Repository(value = "MemberDAO")
public class MemberDAO {

    @Autowired
    SqlSession sqlSession;

    private static final String NAMESPACE = "com.spring.login.MemberMapper";


    public int joinMember(MemberDTO memberDTO){
        return sqlSession.getMapper(MemberMapper.class).joinMember(memberDTO);

    }

    public MemberDTO loadMember(MemberDTO memberDTO){
        return sqlSession.getMapper(MemberMapper.class).loadMember(memberDTO);
    }

    public int checkID(String id){
        return sqlSession.selectOne(NAMESPACE+".checkID", id);
    }

    public int updateMember(MemberDTO memberDTO) {
        return sqlSession.update(NAMESPACE+".updateMember", memberDTO);
    }

    public int deleteMember(String id ) {
        return sqlSession.delete(NAMESPACE+".deleteMember", id);
    }
}
