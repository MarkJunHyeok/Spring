package com.spring.notice;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

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

    public int noticePageCount(String inputSearch, String selectSearch) {
        return sqlSession.getMapper(NoticeMapper.class).noticePageCount(inputSearch, selectSearch);
    }

    public List<NoticeDTO> noticeLoad(Map<String, Object> param) {
        try{
            return sqlSession.selectList(NAMESPACE+".noticeLoad", param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
