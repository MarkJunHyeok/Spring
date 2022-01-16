package com.spring.notice;

import java.util.List;

public interface NoticeMapper {

    int noticeCreate(NoticeDTO noticeDTO);

    int noticeUpdate(NoticeDTO noticeDTO);

    int noticeDelete(String num);

    void viewsUpdate(int num);




    int noticePageCount();

    NoticeDTO noticeView(int num);

    List<NoticeDTO> noticeLoad(PageDTO page);
}
