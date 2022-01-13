package com.spring.login;

import javax.servlet.http.HttpServletRequest;

public interface MemberMapper {

    int joinMember(MemberDTO m);


    int checkID(String id);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(String id);

    MemberDTO loadMember(MemberDTO memberDTO);
}
