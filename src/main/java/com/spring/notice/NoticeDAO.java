package com.spring.notice;

import com.spring.login.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository(value = "NoticeDAO")
public class NoticeDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.spring.notice.NoticeMapper";

    public int noticeCreate(NoticeDTO noticeDTO) {
        return sqlSession.insert(NAMESPACE+".noticeCreate", noticeDTO);
    }


    public NoticeDTO noticeView(int num){
        return sqlSession.selectOne(NAMESPACE+".noticeView", num);
    }

    public void viewsUpdate(int num){
        sqlSession.update(NAMESPACE+".viewsUpdate", num);
    }

    public int noticeUpdate(NoticeDTO noticeDTO) {
        return sqlSession.update(NAMESPACE+".noticeUpdate", noticeDTO);
    }

    public int noticeDelete(int num) {
        return sqlSession.delete(NAMESPACE+".noticeDelete", num);
    }

    public int noticePageCount() {
        return sqlSession.selectOne(NAMESPACE+".noticePageCount");
    }

    public List<NoticeDTO> noticeLoad(PageDTO page) {
        return sqlSession.selectList(NAMESPACE+".noticeLoad" , page);
    }
}
