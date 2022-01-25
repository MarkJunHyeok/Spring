package com.spring.notice;



import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {

    int noticeCreate(NoticeDTO noticeDTO);

    int noticeUpdate(NoticeDTO noticeDTO);

    int noticeDelete(String num);

    void viewsUpdate(int num);




    int noticePageCount(@Param("INPUT")String input , @Param("SELECT")String select);

    NoticeDTO noticeView(int num);

    List<NoticeDTO> noticeLoad(Map<String, Object> param);
}
