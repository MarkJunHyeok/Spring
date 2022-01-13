package com.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    MemberDAO memberDAO;


    //로그인 페이지
    @RequestMapping(value = "/loginPage" ,method = RequestMethod.GET)
    public String loginPage(HttpServletRequest req){
        req.setAttribute("content", "login/login.jsp");
        return "home";
    }

    //회원가입 페이지
    @RequestMapping(value = "/joinPage" ,method = RequestMethod.GET)
    public String joinPage(HttpServletRequest req){
        if (req.getSession().getAttribute("loginMember") != null){
            MemberDTO memberDTO = (MemberDTO) req.getSession().getAttribute("loginMember");
            String[] array = memberDTO.getMi_addr().split("!");
            req.getSession().setAttribute("arr" , array);
        }


        req.setAttribute("content", "login/join.jsp");
        return "home";
    }

    //회원가입 처리 로직  & 회원정보 수정
    @RequestMapping( value = "/joinGo" , method = RequestMethod.POST)
    public String joinGo(HttpServletRequest req , MemberDTO memberDTO ){
        if( req.getSession().getAttribute("loginMember") == null){
            int result = memberDAO.joinMember(req , memberDTO);
            if( result == 0){
                req.setAttribute("MSG" , "회원가입에 실패하셨습니다.");
                req.setAttribute("content", "login/login.jsp");
            }else{
                req.setAttribute("MSG", "회원가입에 성공하셨습니다.");
                req.setAttribute("content", "main.jsp");
            }
        }else{
            //회원정보 수정
            int result = memberDAO.updateMember(req, memberDTO);
            if(result == 0){
                req.setAttribute("MSG" , "정보수정을 실패하였습니다.");
                req.setAttribute("content" , "main.jsp");
            }else{
                MemberDTO loadMember = memberDAO.loadMember(memberDTO);
                req.getSession().setAttribute("loginMember", loadMember);
                req.setAttribute("MSG" , "정보수정을 완료 하였습니다.");
                req.setAttribute("content" , "main.jsp");
            }

        }

        return "home";
    }
    //로그인 구현
    @RequestMapping( value = "/loginGo" , method = RequestMethod.POST)
    public String loginGo(HttpServletRequest req, MemberDTO memberDTO){
        MemberDTO loginMember = memberDAO.loadMember(memberDTO);
        if(loginMember == null){
            req.setAttribute("content" , "main.jsp");
            req.setAttribute("MSG" , "해당 계정은 존재 하지 않습니다.");
        }else{
            req.getSession().setAttribute("loginMember", loginMember);
            req.setAttribute("MSG" , "로그인에 성공하셨습니다.");
            req.setAttribute("content", "main.jsp");
        }
        return "home";
    }


    //로그아웃
    @RequestMapping (value = "/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        req.setAttribute("content", "main.jsp");
        req.setAttribute("MSG" , "로그아웃 되었습니다.");
        return "home";
    }

    //아이디 중복체크
    @RequestMapping(value ="/checkID" , method = RequestMethod.POST)
    @ResponseBody
    public String checkID(HttpServletRequest req){
        int result = memberDAO.checkID(req);
        System.out.println(req.getParameter("mi_id"));
        if(result == 0){
            return "y";
        }else{
            return "n";
        }
    }

    //회원탈퇴
    @RequestMapping (value = "/deleteMember" , method = RequestMethod.POST)
    @ResponseBody
    public String deleteMember(HttpServletRequest req){
        int result = memberDAO.deleteMember(req);
        if( result == 0){
            return "n";
        }else{
            req.getSession().invalidate();
            return "y";
        }
    }
}
